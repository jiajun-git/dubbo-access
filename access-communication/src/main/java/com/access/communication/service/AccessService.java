package com.access.communication.service;

import cn.hutool.core.util.HexUtil;
import com.access.api.entity.DrDevDoor;
import com.access.api.model.*;
import com.access.api.service.AccessAuthService;
import com.access.api.service.AccessCustPlanService;
import com.access.api.service.AccessDevService;
import com.access.api.service.AccessVerService;
import com.access.communication.netty.constants.AccessConstants;
import com.access.communication.netty.dto.DevAllDto;
import com.access.communication.netty.dto.DevAskDto;
import com.access.communication.netty.dto.DevReceiveDto;
import com.access.communication.netty.dto.DevReplyDto;
import com.access.communication.netty.dto.custgroup.CustGroupDto;
import com.access.communication.netty.dto.custgroup.CustGroupReplyDto;
import com.access.communication.netty.dto.custgroup.DevCustGroupDto;
import com.access.communication.netty.dto.group.DevGroupDto;
import com.access.communication.netty.dto.group.GroupDto;
import com.access.communication.netty.dto.group.GroupReplyDto;
import com.access.communication.netty.dto.heartbeat.DoorVerDto;
import com.access.communication.netty.dto.heartbeat.HeartbeatDto;
import com.access.communication.netty.dto.heartbeat.HeartbeatReplyDto;
import com.access.communication.netty.dto.heartbeat.HeartbeatReqDto;
import com.access.communication.netty.dto.register.DoorInfoDto;
import com.access.communication.netty.dto.register.RegisterReplyDto;
import com.access.communication.netty.dto.user.DevUserDto;
import com.access.communication.netty.dto.user.UserDto;
import com.access.communication.netty.dto.user.UserReplyDto;
import com.access.communication.netty.dto.userplan.DevUserPlanDto;
import com.access.communication.netty.dto.userplan.UserPlanDetailDto;
import com.access.communication.netty.dto.userplan.UserPlanDto;
import com.access.communication.netty.dto.userplan.UserPlanReplyDto;
import com.access.communication.netty.dto.visitor.DevVisitorDto;
import com.access.communication.netty.dto.visitor.VisitorDto;
import com.access.communication.netty.dto.visitor.VisitorReplyDto;
import com.access.communication.utils.AccessUtils;
import com.access.communication.utils.CrcUtils;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccessService {

    @Reference(version = "1.0.0")
    private AccessDevService accessDevService;

    @Reference(version = "1.0.0")
    private AccessVerService accessVerService;

    @Reference(version = "1.0.0")
    private AccessCustPlanService accessCustPlanService;

    @Reference(version = "1.0.0")
    private AccessAuthService accessAuthService;

    /**
     * 数据集合，key是客户端地址，value是待发送全部数据
     */
    private static ConcurrentHashMap<String, DevAllDto> dataMap = new ConcurrentHashMap<>();

    /**
     * 数据集合，key是客户端地址，value是待发送访客数据
     */
    private static ConcurrentHashMap<String, LinkedList<VisitorReplyDto>> vistorDataMap = new ConcurrentHashMap<>();

    /**
     * 当前发送数据集合,key是客户端地址，value是发送的数据包
     */
    private static ConcurrentHashMap<String, String> currentSendMap = new ConcurrentHashMap<>();

    /**
     * 发送通道集合，key是客户端地址
     */
    private static ConcurrentHashMap<String, ChannelHandlerContext> ctxMap = new ConcurrentHashMap<>();

    /**
     * 发送者集合，key是客户端地址
     */
    private static ConcurrentHashMap<String, InetSocketAddress> senderMap = new ConcurrentHashMap<>();

    /**
     * 发送者集合，key是门禁控制器编号，value是客户端地址
     */
    private static ConcurrentHashMap<String, String> devAddressMap = new ConcurrentHashMap<>();

    /**
     * 解析设备上报数据
     * @param ctx
     * @param content
     * @param recipient
     */
    public void handleData(ChannelHandlerContext ctx, String content, InetSocketAddress recipient){
        ctxMap.put(recipient.toString(), ctx);
        senderMap.put(recipient.toString(), recipient);
        //crc16校验
        if(!CrcUtils.validateData(content)){
            log.error("CRC16校验失败，【{}】", content);
            return;
        }
        //获取数据部分
        DevReceiveDto devReceiveDto = AccessUtils.parseData(content);
        switch (devReceiveDto.getType()) {
            case AccessConstants.FUNC_REGISTER:
                //签到
                register(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_HEARTBEAT:
                //心跳
                heartbeat(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_USERPLAN:
                //用户计划
                sendUserPlan(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_GROUP:
                //权限组
                sendGroup(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_CUST:
                //用户名单
                sendCust(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_CUST_GROUP:
                //用户权限组
                sendCustGroup(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_VISITOR:
                //临时名单
                sendVisitor(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_RECORD:
                //接收开门记录
                receiveRecord(ctx, devReceiveDto.getData(), recipient);
                break;
            case AccessConstants.FUNC_END:
                //流程结束
                log.info("流程结束");
                break;
            default:
                log.error("未知功能码【{}】", devReceiveDto.getType());
                break;
        }
    }

    /**
     * 签到
     * @param ctx
     * @param data
     * @param recipient
     */
    private void register(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "门禁控制器签到，【{}】，【{}】", recipient.toString(), data);
        JSONObject devInfoObj = JSONObject.parseObject(data);
        String devSn = devInfoObj.getString("dev_sn");
        //每次签到覆盖之前数据
        DevAllDto devAllDto = new DevAllDto();
        devAllDto.setDevSn(devSn);
        devAllDto.setAddress(recipient.toString());
        dataMap.put(recipient.toString(), devAllDto);
        //根据版本号查询待下发名单
        List<DoorInfoDto> list = getDoorList(devSn);
        if(list.size() > 0){
            RegisterReplyDto registerReplyDto = new RegisterReplyDto();
            registerReplyDto.setDoor_array(list);
            String registerData = AccessUtils.generateData(JSONObject.toJSONString(registerReplyDto), AccessConstants.FUNC_REGISTER, AccessConstants.MSG_DATA, AccessConstants.DEFAULT_SEQ);
            sendData(ctx, registerData, recipient);
        }else{
            log.warn("未查询到门禁控制器【{}】", devSn);
            //sendEnd(ctx, recipient);
        }
    }

    /**
     * 处理心跳数据
     * @param ctx
     * @param data
     * @param recipient
     */
    private void heartbeat(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "设备心跳，【{}】，【{}】", recipient.toString(), data);
        HeartbeatReqDto heartbeatReqDto = JSONObject.parseObject(data, HeartbeatReqDto.class);
        List<DoorVerDto> doorArr = heartbeatReqDto.getDoor_array();
        if(null != doorArr && doorArr.size() > 0){
            //组装数据
            DevAllDto devAllDto = getAllData(heartbeatReqDto);
            dataMap.put(recipient.toString(), devAllDto);
            devAddressMap.put(devAllDto.getDevSn(), recipient.toString());
            HeartbeatReplyDto heartbeatReplyDto = devAllDto.getHeartbeatReplyDto();
            String heartbeatData = AccessUtils.generateData(JSONObject.toJSONString(heartbeatReplyDto), AccessConstants.FUNC_HEARTBEAT, AccessConstants.MSG_DATA, AccessConstants.DEFAULT_SEQ);
            sendData(ctx, heartbeatData, recipient);
            //发送下一步消息
            sendNext(ctx, recipient);
        }else{
            log.error( "设备心跳异常，【{}】，【{}】", recipient.toString(), data);
            sendEnd(ctx, recipient);
        }
    }

    /**
     * 下发用户计划
     * @param ctx
     * @param data
     * @param recipient
     */
    private void sendUserPlan(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "设备用户计划，【{}】，【{}】", recipient.toString(), data);
        DevReplyDto devReplyDto = JSONObject.parseObject(data, DevReplyDto.class);
        DevAllDto devAllDto = dataMap.get(recipient.toString());
        if(devReplyDto.getStatus() == 0){
            LinkedList<UserPlanReplyDto> userPlanReplyList = devAllDto.getUserPlanReplyList();
            if(null != userPlanReplyList && userPlanReplyList.size() > 0){
                String nextDevSn = userPlanReplyList.get(0).getDevSn();
                if(StringUtils.equals(devReplyDto.getDev_sn(), nextDevSn)){
                    UserPlanReplyDto userPlanReplyDto = userPlanReplyList.poll();
                    devAllDto.setUserPlanReplyList(userPlanReplyList);
                    dataMap.put(recipient.toString(), devAllDto);
                    String sendUserPlanData = AccessUtils.generateData(JSONObject.toJSONString(userPlanReplyDto.getDevUserPlanDto()), AccessConstants.FUNC_USERPLAN, AccessConstants.MSG_DATA, userPlanReplyDto.getSeq());
                    sendData(ctx, sendUserPlanData, recipient);
                }else{
                    //不同的设备，说明新开始一个，询问是否能下发用户计划
                    askUserPlan(ctx, userPlanReplyList.get(0).getDevSn(), recipient);
                }
            }else{
                log.info("用户计划下发完成");
                //发送下一步消息
                sendNext(ctx, recipient);
            }
        }else{
            //硬件设备未准备好，跳过，下发权限组
            log.error("硬件设备未准备好，跳过用户计划下发");
            devAllDto.setUserPlanReplyList(new LinkedList<>());
            dataMap.put(recipient.toString(), devAllDto);
            //发送下一步消息
            sendNext(ctx, recipient);
        }
    }

    /**
     * 下发权限组
     * @param ctx
     * @param data
     * @param recipient
     */
    private void sendGroup(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "设备权限组，【{}】，【{}】", recipient.toString(), data);
        DevReplyDto devReplyDto = JSONObject.parseObject(data, DevReplyDto.class);
        DevAllDto devAllDto = dataMap.get(recipient.toString());
        LinkedList<GroupReplyDto> groupList = devAllDto.getGroupReplyList();
        if(devReplyDto.getStatus() == 0){
            if(null != groupList && groupList.size() > 0){
                String nextDevSn = groupList.get(0).getDevSn();
                if(StringUtils.equals(devReplyDto.getDev_sn(), nextDevSn)){
                    GroupReplyDto groupReplyDto = groupList.poll();
                    devAllDto.setGroupReplyList(groupList);
                    dataMap.put(recipient.toString(), devAllDto);
                    String sendGroupData = AccessUtils.generateData(JSONObject.toJSONString(groupReplyDto.getDevGroupDto()), AccessConstants.FUNC_GROUP, AccessConstants.MSG_DATA, groupReplyDto.getSeq());
                    sendData(ctx, sendGroupData, recipient);
                }else{
                    //不同的设备，说明新开始一个，询问是否能下发权限组
                    askGroup(ctx, nextDevSn, recipient);
                }
            }else{
                log.info("权限组下发完成");
                //发送下一步消息
                sendNext(ctx, recipient);
            }
        }else{
            //硬件设备未准备好，跳过下发权限组
            log.error("硬件设备未准备好，跳过权限组下发");
            devAllDto.setGroupReplyList(new LinkedList<>());
            dataMap.put(recipient.toString(), devAllDto);
            //发送下一步消息
            sendNext(ctx, recipient);
        }
    }

    /**
     * 下发权限组
     * @param ctx
     * @param data
     * @param recipient
     */
    private void sendCust(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "设备用户名单，【{}】，【{}】", recipient.toString(), data);
        DevReplyDto devReplyDto = JSONObject.parseObject(data, DevReplyDto.class);
        DevAllDto devAllDto = dataMap.get(recipient.toString());
        if(devReplyDto.getStatus() == 0){
            LinkedList<UserReplyDto> userList = devAllDto.getUserReplyList();
            if(null != userList && userList.size() > 0){
                String nextDevSn = userList.get(0).getDevSn();
                if(StringUtils.equals(devReplyDto.getDev_sn(), nextDevSn)){
                    UserReplyDto userReplyDto = userList.poll();
                    devAllDto.setUserReplyList(userList);
                    dataMap.put(recipient.toString(), devAllDto);
                    String sendCustData = AccessUtils.generateData(JSONObject.toJSONString(userReplyDto.getDevUserDto()), AccessConstants.FUNC_CUST, AccessConstants.MSG_DATA, userReplyDto.getSeq());
                    sendData(ctx, sendCustData, recipient);
                }else{
                    //不同的设备，说明新开始一个，询问是否能下发用户名单
                    askCust(ctx, nextDevSn, recipient, devAllDto.getUserSynType());
                }
            }else{
                log.info("用户名单下发完成");
                //发送下一步消息
                sendNext(ctx, recipient);
            }
        }else{
            //硬件设备未准备好，跳过下发权限组
            log.error("硬件设备未准备好，跳过人员名单下发");
            devAllDto.setUserReplyList(new LinkedList<>());
            dataMap.put(recipient.toString(), devAllDto);
            //发送下一步消息
            sendNext(ctx, recipient);
        }
    }

    /**
     * 发送人组关系
     * @param ctx
     * @param data
     * @param recipient
     */
    private void sendCustGroup(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "设备用户权限组，【{}】，【{}】", recipient.toString(), data);
        DevReplyDto devReplyDto = JSONObject.parseObject(data, DevReplyDto.class);
        DevAllDto devAllDto = dataMap.get(recipient.toString());
        if(devReplyDto.getStatus() == 0){
            LinkedList<CustGroupReplyDto> custGroupReplyList = devAllDto.getCustGroupReplyList();
            if(null != custGroupReplyList && custGroupReplyList.size() > 0){
                String nextDevSn = custGroupReplyList.get(0).getDevSn();
                if(StringUtils.equals(devReplyDto.getDev_sn(), nextDevSn)){
                    CustGroupReplyDto custGroupReplyDto = custGroupReplyList.poll();
                    devAllDto.setCustGroupReplyList(custGroupReplyList);
                    dataMap.put(recipient.toString(), devAllDto);
                    String sendCustGroupData = AccessUtils.generateData(JSONObject.toJSONString(custGroupReplyDto.getDevCustGroupDto()), AccessConstants.FUNC_CUST_GROUP, AccessConstants.MSG_DATA, custGroupReplyDto.getSeq());
                    sendData(ctx, sendCustGroupData, recipient);
                }else{
                    //不同的设备，说明新开始一个，询问是否能下发用户权限组
                    askCustGroup(ctx, nextDevSn, recipient, devAllDto.getCustGroupSynType());
                }
            }else{
                log.info("用户权限组下发完成，流程结束");
                sendEnd(ctx, recipient);
                currentSendMap.put(recipient.toString(), "");
            }
        }else{
            //硬件设备未准备好，直接下发流程结束
            log.error("硬件设备未准备好，流程结束");
            sendEnd(ctx, recipient);
        }
    }

    /**
     * 接收上报记录
     * @param ctx
     * @param data
     * @param recipient
     */
    private void receiveRecord(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.info( "设备开门记录，【{}】，【{}】", recipient.toString(), data);
    }

    /**
     * 发送数据
     * @param ctx
     * @param content
     * @param recipient
     */
    private void sendData(ChannelHandlerContext ctx, String content, InetSocketAddress recipient){
        currentSendMap.put(recipient.toString(), content);
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes(HexUtil.decodeHex(content));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, recipient));
    }

    /**
     * 发送数据
     * @param ctx
     * @param content
     * @param recipient
     */
    private void sendNoRetryData(ChannelHandlerContext ctx, String content, InetSocketAddress recipient){
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes(HexUtil.decodeHex(content));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, recipient));
    }

    /**
     * 发送数据
     * @param ctx
     * @param recipient
     */
    private void sendNext(ChannelHandlerContext ctx, InetSocketAddress recipient){
        DevAllDto devAllDto = dataMap.get(recipient.toString());
        if(null != devAllDto){
            if(null != devAllDto.getUserPlanReplyList() && devAllDto.getUserPlanReplyList().size() > 0){
                //发送下发用户计划询问
                String devSn = devAllDto.getUserPlanReplyList().get(0).getDevSn();
                askUserPlan(ctx, devSn, recipient);
            }else if(null != devAllDto.getGroupReplyList() && devAllDto.getGroupReplyList().size() > 0){
                //无用户计划，下发权限组询问
                String devSn = devAllDto.getGroupReplyList().get(0).getDevSn();
                askGroup(ctx, devSn, recipient);
            }else if(null != devAllDto.getUserReplyList() && devAllDto.getUserReplyList().size() > 0){
                //无用户计划，下发权限组询问
                String devSn = devAllDto.getUserReplyList().get(0).getDevSn();
                askCust(ctx, devSn, recipient, devAllDto.getUserSynType());
            }else if(null != devAllDto.getCustGroupReplyList() && devAllDto.getCustGroupReplyList().size() > 0){
                //无用户计划，下发权限组询问
                String devSn = devAllDto.getCustGroupReplyList().get(0).getDevSn();
                askCustGroup(ctx, devSn, recipient, devAllDto.getCustGroupSynType());
            }else{
                sendEnd(ctx, recipient);
            }
        }else{
            sendEnd(ctx, recipient);
        }
    }

    /**
     * 下发用户计划询问
     * @param ctx
     * @param devSn 设备编号
     * @param recipient
     */
    private void askUserPlan(ChannelHandlerContext ctx, String devSn, InetSocketAddress recipient){
        //发送下发用户计划询问
        DevAskDto devAskDto = new DevAskDto();
        devAskDto.setDev_sn(devSn);
        devAskDto.setSign(1);
        String askUserPlanData = AccessUtils.generateData(JSONObject.toJSONString(devAskDto), AccessConstants.FUNC_USERPLAN, AccessConstants.MSG_ASK, AccessConstants.DEFAULT_SEQ);
        sendData(ctx, askUserPlanData, recipient);
    }

    /**
     * 下发权限组询问
     * @param ctx
     * @param devSn 设备编号
     * @param recipient
     */
    private void askGroup(ChannelHandlerContext ctx, String devSn, InetSocketAddress recipient){
        //发送下发用户计划询问
        DevAskDto devAskDto = new DevAskDto();
        devAskDto.setDev_sn(devSn);
        devAskDto.setSign(0);
        String askGroupData = AccessUtils.generateData(JSONObject.toJSONString(devAskDto), AccessConstants.FUNC_GROUP, AccessConstants.MSG_ASK, AccessConstants.DEFAULT_SEQ);
        sendData(ctx, askGroupData, recipient);
    }

    /**
     * 下发用户名单询问
     * @param ctx
     * @param devSn 设备编号
     * @param recipient
     */
    private void askCust(ChannelHandlerContext ctx, String devSn, InetSocketAddress recipient, Integer synType){
        //发送下发用户计划询问
        DevAskDto devAskDto = new DevAskDto();
        devAskDto.setDev_sn(devSn);
        devAskDto.setSign(synType);
        String askCustData = AccessUtils.generateData(JSONObject.toJSONString(devAskDto), AccessConstants.FUNC_CUST, AccessConstants.MSG_ASK, AccessConstants.DEFAULT_SEQ);
        sendData(ctx, askCustData, recipient);
    }

    /**
     * 下发用户权限组询问
     * @param ctx
     * @param devSn 设备编号
     * @param recipient
     */
    private void askCustGroup(ChannelHandlerContext ctx, String devSn, InetSocketAddress recipient, Integer synType){
        //发送下发用户权限组询问
        DevAskDto devAskDto = new DevAskDto();
        devAskDto.setDev_sn(devSn);
        devAskDto.setSign(synType);
        String askCustGroupData = AccessUtils.generateData(JSONObject.toJSONString(devAskDto), AccessConstants.FUNC_CUST_GROUP, AccessConstants.MSG_ASK, AccessConstants.DEFAULT_SEQ);
        sendData(ctx, askCustGroupData, recipient);
    }

    /**
     * 下发访客名单
     * @param ctx
     * @param devSn 设备编号
     * @param recipient
     */
    private void askVisitor(ChannelHandlerContext ctx, String devSn, InetSocketAddress recipient){
        //发送下发用户权限组询问
        DevAskDto devAskDto = new DevAskDto();
        devAskDto.setDev_sn(devSn);
        devAskDto.setSign(1);
        String askVisitorData = AccessUtils.generateData(JSONObject.toJSONString(devAskDto), AccessConstants.FUNC_VISITOR, AccessConstants.MSG_ASK, AccessConstants.DEFAULT_SEQ);
        sendNoRetryData(ctx, askVisitorData, recipient);
    }

    /**
     * 获取当前正在发送的数据
     * @return
     */
    public Map<String, String> getSendingData(){
        return currentSendMap;
    }

    /**
     * 重发数据
     * @return
     */
    public void resendData(String address, String content){
        sendData(ctxMap.get(address), content, senderMap.get(address));
    }

    /**
     * 发送访客数据
     * @param devSn 门禁编号，需要转换成控制器编号
     * @param visitorReplyList
     */
    public void sendVisitor(String devSn, LinkedList<VisitorReplyDto> visitorReplyList){
        String address = devAddressMap.get(devSn.substring(0, devSn.length() - 1));
        if(StringUtils.isNotBlank(address) && null != ctxMap.get(address) && null != senderMap.get(address)){
            vistorDataMap.put(address, visitorReplyList);
            askVisitor(ctxMap.get(address), devSn, senderMap.get(address));
        }
    }

    /**
     * 下发临时名单
     * @param ctx
     * @param data
     * @param recipient
     */
    private void sendVisitor(ChannelHandlerContext ctx, String data, InetSocketAddress recipient){
        log.debug( "设备临时名单，【{}】，【{}】", recipient.toString(), data);
        DevReplyDto devReplyDto = JSONObject.parseObject(data, DevReplyDto.class);
        if(devReplyDto.getStatus() == 0){
            LinkedList<VisitorReplyDto> visitorReplyList = vistorDataMap.get(recipient.toString());
            if(null != visitorReplyList && visitorReplyList.size() > 0){
                String nextDevSn = visitorReplyList.get(0).getDevSn();
                if(StringUtils.equals(devReplyDto.getDev_sn(), nextDevSn)){
                    VisitorReplyDto visitorReplyDto = visitorReplyList.poll();
                    vistorDataMap.put(recipient.toString(), visitorReplyList);
                    String sendVisitorData = AccessUtils.generateData(JSONObject.toJSONString(visitorReplyDto.getDevVisitorDto()), AccessConstants.FUNC_VISITOR, AccessConstants.MSG_DATA, visitorReplyDto.getSeq());
                    sendNoRetryData(ctx, sendVisitorData, recipient);
                }else{
                    //不同的设备，说明新开始一个，询问是否能下发访客名单
                    askVisitor(ctx, nextDevSn, recipient);
                }
            }else{
                //更新访客发送状态
                log.debug("访客名单下发完成，流程结束");
            }
        }else{
            //硬件设备未准备好，直接下发流程结束
            log.debug("硬件设备未准备好");
        }
    }

    private void sendEnd(ChannelHandlerContext ctx, InetSocketAddress recipient){
        //发送下发用户权限组询问
        DevAllDto devAllDto = dataMap.get(recipient.toString());
        DevAskDto devAskDto = new DevAskDto();
        devAskDto.setDev_sn(devAllDto.getDevSn());
        String sendEndData = AccessUtils.generateData(JSONObject.toJSONString(devAskDto), AccessConstants.FUNC_END, AccessConstants.MSG_ASK, AccessConstants.DEFAULT_SEQ);
        sendData(ctx, sendEndData, recipient);
    }


    /**
     * 获取门禁控制信息
     * @param devSn
     * @return
     */
    public List<DoorInfoDto> getDoorList(String devSn){
        List<AccessDevInitDto> accessList = accessDevService.getDevInitDate(devSn);
        List<DoorInfoDto> doorList = new ArrayList<>();
        for (int i = 0; i < accessList.size(); i++) {
            DoorInfoDto doorInfoDto = new DoorInfoDto();
            BeanUtils.copyProperties(accessList.get(i), doorInfoDto);
            doorList.add(doorInfoDto);
        }
        return doorList;
    }

    /**
     * 获取门禁延迟参数
     * @param doorArr
     * @return
     */
    public List<HeartbeatDto> getHeartbeatList(List<DoorVerDto> doorArr){
        List<HeartbeatDto> heartbeatList = new ArrayList<>();
        for (int i = 0; i < doorArr.size(); i++) {
            String devSn = doorArr.get(i).getDev_sn();
            String devCode = devSn.substring(0, devSn.length() - 1);
            Integer mac = Integer.valueOf(devSn.substring(devSn.length() - 1));
            DrDevDoor drDevDoor = accessDevService.getDevInfoByDevCodeAndMac(devCode, mac);
            HeartbeatDto heartbeatDto = new HeartbeatDto();
            heartbeatDto.setDev_sn(devSn);
            heartbeatDto.setTimeout_limit(drDevDoor.getOpenlocktime());
            heartbeatList.add(heartbeatDto);
        }
        return heartbeatList;
    }

    /**
     * 组装待下发所有数据
     * @param heartbeatReqDto
     * @return
     */
    public DevAllDto getAllData(HeartbeatReqDto heartbeatReqDto){
        DevAllDto devAllDto = new DevAllDto();
        //判断增量还是全量
        devAllDto.setUserSynType(heartbeatReqDto.getCust_ver() == 0 ? 1 : 0);
        devAllDto.setCustGroupSynType(heartbeatReqDto.getCustgroup_ver() == 0 ? 1 : 0);
        //心跳数据
        List<HeartbeatDto> heartbeatList = new ArrayList<>();
        //用户计划
        LinkedList<UserPlanReplyDto> userPlanReplyList = new LinkedList<>();
        //权限组
        LinkedList<GroupReplyDto> groupReplyList = new LinkedList<>();
        //用户名单
        LinkedList<UserReplyDto> userReplyList = new LinkedList<>();
        //人组关系
        LinkedList<CustGroupReplyDto> custGroupReplyList = new LinkedList<>();
        List<DoorVerDto> doorArr = heartbeatReqDto.getDoor_array();
        //名单最大版本号
        int maxCustVer = 0;
        int maxCustGroupVer = 0;
        //控制器编号
        String accessSn = "";
        for (int i = 0; i < doorArr.size(); i++) {
            DoorVerDto doorVerDto = doorArr.get(i);
            String devSn = doorVerDto.getDev_sn();
            String devCode = devSn.substring(0, devSn.length() - 1);
            accessSn = devCode;
            Integer mac = Integer.valueOf(devSn.substring(devSn.length() - 1));
            //查询门禁设备信息
            DrDevDoor drDevDoor = accessDevService.getDevInfoByDevCodeAndMac(devCode, mac);
            //获取延迟关门时间
            HeartbeatDto heartbeatDto = new HeartbeatDto();
            heartbeatDto.setDev_sn(devSn);
            heartbeatDto.setTimeout_limit(drDevDoor.getOpenlocktime());
            heartbeatList.add(heartbeatDto);
            //查询版本号
            HeartBeatDto heartBeatDto = accessVerService.queryAccessDevVer(drDevDoor.getDevid());
            maxCustVer = heartBeatDto.getCustVer();
            maxCustGroupVer = heartBeatDto.getCustgroupVer();
            //组装用户计划数据
            userPlanReplyList.addAll(getUserPlanList(devSn, heartBeatDto.getCustplanVer(), doorVerDto.getCustplan_ver()));
            //组装权限组数据
            groupReplyList.addAll(getGroupList(devSn, drDevDoor, heartBeatDto.getGroupVer(), doorVerDto.getGroup_ver()));
        }
        //组装人组关系数据
        custGroupReplyList.addAll(getCustGroupList(accessSn, maxCustGroupVer, heartbeatReqDto.getCustgroup_ver()));
        //组装用户名单数据
        userReplyList.addAll(getUserList(accessSn, maxCustVer, heartbeatReqDto.getCust_ver()));
        //组装心跳数据
        HeartbeatReplyDto heartbeatReplyDto = new HeartbeatReplyDto();
        heartbeatReplyDto.setDoor_array(heartbeatList);
        heartbeatReplyDto.setSys_time(System.currentTimeMillis()/1000);
        devAllDto.setHeartbeatReplyDto(heartbeatReplyDto);
        devAllDto.setUserPlanReplyList(userPlanReplyList);
        devAllDto.setGroupReplyList(groupReplyList);
        devAllDto.setUserReplyList(userReplyList);
        devAllDto.setCustGroupReplyList(custGroupReplyList);
        devAllDto.setDevSn(accessSn);
        return devAllDto;
    }

    /**
     * 组装用户计划
     * @param devSn
     * @param maxCustplanVer
     * @param custplanVer
     * @return
     */
    public LinkedList<UserPlanReplyDto> getUserPlanList(String devSn, Integer maxCustplanVer, Integer custplanVer){
        LinkedList<UserPlanReplyDto> userPlanReplyList = new LinkedList<>();
        //判断是否需要更新用户计划
        if(maxCustplanVer > custplanVer){
            //返回给设备集合
            List<UserPlanDto> userPlanList = new ArrayList<>();
            //数据库返回用户计划明细，需按设备分组
            List<AccessCustPlanDto> planList= accessCustPlanService.queryPlanList(maxCustplanVer);
            //按planId分组
            Map<Integer, List<AccessCustPlanDto>> groupIdList = planList.stream().collect(Collectors.groupingBy(AccessCustPlanDto::getPlan_id, Collectors.toList()));
            for (Integer planId : groupIdList.keySet()) {
                UserPlanDto userPlanDto = new UserPlanDto();
                userPlanDto.setPi(planId);
                List<UserPlanDetailDto> planDetailList = new ArrayList<>();
                for (int i = 0; i < groupIdList.get(planId).size(); i++) {
                    AccessCustPlanDto accessCustPlanDto = groupIdList.get(planId).get(i);
                    UserPlanDetailDto userPlanDetailDto = new UserPlanDetailDto();
                    userPlanDetailDto.setW(accessCustPlanDto.getWeek());
                    userPlanDetailDto.setBh(accessCustPlanDto.getBegin_hour());
                    userPlanDetailDto.setBm(accessCustPlanDto.getBegin_minute());
                    userPlanDetailDto.setEh(accessCustPlanDto.getEnd_hour());
                    userPlanDetailDto.setEm(accessCustPlanDto.getEnd_minute());
                    planDetailList.add(userPlanDetailDto);
                }
                userPlanDto.setPd(planDetailList);
                userPlanList.add(userPlanDto);
            }
            int stepLength = 3;
            int frameNum = (int) Math.ceil(((double)userPlanList.size())/stepLength);
            for (int k = 0; k < frameNum; k++) {
                UserPlanReplyDto userPlanReplyDto = new UserPlanReplyDto();
                userPlanReplyDto.setDevSn(devSn);
                userPlanReplyDto.setSeq(AccessUtils.getSeq(k));
                DevUserPlanDto devUserPlanDto = new DevUserPlanDto();
                devUserPlanDto.setDev_sn(devSn);
                List<UserPlanDto> subPlanList = userPlanList.subList(k*stepLength, (k+1)*stepLength < userPlanList.size()  ? (k+1)*stepLength : userPlanList.size());
                devUserPlanDto.setPlan_arr(subPlanList);
                devUserPlanDto.setMax_ver(maxCustplanVer);
                devUserPlanDto.setIs_end((k+1)*stepLength < userPlanList.size() ? 0 : 1);
                userPlanReplyDto.setDevUserPlanDto(devUserPlanDto);
                userPlanReplyList.add(userPlanReplyDto);
            }
        }
        return userPlanReplyList;
    }

    /**
     * 组装权限组
     * @param devSn
     * @param drDevDoor
     * @param maxGroupVer
     * @param groupVer
     * @return
     */
    public LinkedList<GroupReplyDto> getGroupList(String devSn, DrDevDoor drDevDoor, Integer maxGroupVer, Integer groupVer){
        //权限组
        LinkedList<GroupReplyDto> groupReplyList = new LinkedList<>();
        //判断是否需要更新权限组
        if(maxGroupVer > groupVer){
            List<GroupDto> groupList = new ArrayList<>();
            List<AccessGroupDto> accessGroupList = accessAuthService.getAccessGroupList(drDevDoor.getDevid());
            for (int i = 0; i < accessGroupList.size(); i++) {
                GroupDto groupDto = new GroupDto();
                BeanUtils.copyProperties(accessGroupList.get(i), groupDto);
                groupList.add(groupDto);
            }
            int stepLength = 3;
            int frameNum = (int) Math.ceil(((double)groupList.size())/stepLength);
            for (int k = 0; k < frameNum; k++) {
                GroupReplyDto groupReplyDto = new GroupReplyDto();
                groupReplyDto.setDevSn(devSn);
                groupReplyDto.setSeq(AccessUtils.getSeq(k));
                DevGroupDto devGroupDto = new DevGroupDto();
                devGroupDto.setDev_sn(devSn);
                List<GroupDto> subGroupList = groupList.subList(k*stepLength, (k+1)*stepLength < groupList.size() ? (k+1)*stepLength : groupList.size());
                devGroupDto.setGroup_arr(subGroupList);
                devGroupDto.setMax_ver(maxGroupVer);
                devGroupDto.setIs_end((k+1)*stepLength < groupList.size() ? 0 : 1);
                groupReplyDto.setDevGroupDto(devGroupDto);
                groupReplyList.add(groupReplyDto);
            }
        }
        return groupReplyList;
    }

    /**
     * 组装用户名单数据
     * @param accessSn  控制器编号
     * @param maxCustVer 人员最大版本号
     * @param custVer 控制器上报的人员版本号
     * @return
     */
    public LinkedList<UserReplyDto> getUserList(String accessSn, Integer maxCustVer, Integer custVer){
        //用户名单
        LinkedList<UserReplyDto> userReplyList = new LinkedList<>();
        //判断是否需要更新用户名单
        if(maxCustVer > custVer){
            List<AccessCustDto> accessCustList = custVer == 0 ? accessAuthService.getAccessCustWholeList(custVer, maxCustVer) : accessAuthService.getAccessCustList(custVer, maxCustVer);
            List<UserDto> userList = new ArrayList<>();
            for (int i = 0; i < accessCustList.size(); i++) {
                UserDto userDto = new UserDto();
                AccessCustDto accessCustDto = accessCustList.get(i);
                userDto.setT(accessCustDto.getType());
                userDto.setCc(accessCustDto.getCust_code());
                userDto.setCn(accessCustDto.getCard_no());
                userDto.setBt(null == accessCustDto.getBegin_time() ? 0: accessCustDto.getBegin_time().getTime()/1000);
                userDto.setEt(null == accessCustDto.getEnd_time() ? 0: accessCustDto.getEnd_time().getTime()/1000);
                userDto.setCp(accessCustDto.getConsum_pwd());
                userDto.setCs(accessCustDto.getCard_status());
                userDto.setIs(accessCustDto.getIs_special());
                userList.add(userDto);
            }
            int stepLength = 60;
            int frameNum = (int) Math.ceil(((double)userList.size())/stepLength);
            for (int k = 0; k < frameNum; k++) {
                UserReplyDto userReplyDto = new UserReplyDto();
                userReplyDto.setDevSn(accessSn);
                userReplyDto.setSeq(AccessUtils.getSeq(k));
                DevUserDto devUserDto = new DevUserDto();
                devUserDto.setDev_sn(accessSn);
                List<UserDto> subUserList = userList.subList(k*stepLength, (k+1)*stepLength < userList.size() ? (k+1)*stepLength : userList.size());
                devUserDto.setCust_arr(subUserList);
                devUserDto.setMax_ver(maxCustVer);
                devUserDto.setIs_end((k+1)*stepLength < userList.size() ? 0 : 1);
                userReplyDto.setDevUserDto(devUserDto);
                userReplyList.add(userReplyDto);
            }
        }
        return userReplyList;
    }

    /**
     * 人组关系数据
     * @param accessSn 控制器编号
     * @param maxCustGroupVer 人组关系最大版本号
     * @param custGroupVer 控制器上报的人组关系版本号
     * @return
     */
    public LinkedList<CustGroupReplyDto> getCustGroupList(String accessSn, Integer maxCustGroupVer, Integer custGroupVer){
        LinkedList<CustGroupReplyDto> custGroupReplyList = new LinkedList<>();
        //判断是否需要更新权限组
        if(maxCustGroupVer > custGroupVer){
            List<CustGroupDto> custGroupList = custGroupVer == 0 ? getAccessCustGroupWholeList(maxCustGroupVer, custGroupVer) : getAccessCustGroupList(maxCustGroupVer, custGroupVer);
            int stepLength = 80;
            int frameNum = (int) Math.ceil(((double)custGroupList.size())/stepLength);
            for (int k = 0; k < frameNum; k++) {
                CustGroupReplyDto custGroupReplyDto = new CustGroupReplyDto();
                custGroupReplyDto.setDevSn(accessSn);
                custGroupReplyDto.setSeq(AccessUtils.getSeq(k));
                DevCustGroupDto devCustGroupDto = new DevCustGroupDto();
                devCustGroupDto.setDev_sn(accessSn);
                List<CustGroupDto> subCustGroupList = custGroupList.subList(k*stepLength, (k+1)*stepLength < custGroupList.size() ? (k+1)*stepLength : custGroupList.size());
                devCustGroupDto.setAccess_arr(subCustGroupList);
                devCustGroupDto.setMax_ver(maxCustGroupVer);
                devCustGroupDto.setIs_end((k+1)*stepLength < custGroupList.size() ? 0 : 1);
                custGroupReplyDto.setDevCustGroupDto(devCustGroupDto);
                custGroupReplyList.add(custGroupReplyDto);
            }
        }
        return custGroupReplyList;
    }

    /**
     * 全量获取人组关系
     * @param maxCustGroupVer
     * @param custGroupVer
     * @return
     */
    private List<CustGroupDto> getAccessCustGroupWholeList(Integer maxCustGroupVer, Integer custGroupVer){
        List<AccessCustGroupWholeDto> accessCustGroupWholeList = accessAuthService.getAccessCustGroupWholeList(custGroupVer, maxCustGroupVer);
        List<CustGroupDto> custGroupList = new ArrayList<>();
        for (int i = 0; i < accessCustGroupWholeList.size(); i++) {
            AccessCustGroupWholeDto accessCustGroupWholeDto = accessCustGroupWholeList.get(i);
            String groupidArr = accessCustGroupWholeDto.getGroupid_arr();
            CustGroupDto custGroupDto = new CustGroupDto();
            custGroupDto.setGroup_id(getGroupHexStr(groupidArr));
            custGroupDto.setCust_code(accessCustGroupWholeDto.getCust_code());
            custGroupDto.setStatus(1); //全量情况下默认新增
            custGroupList.add(custGroupDto);
        }
        return custGroupList;
    }

    /**
     * 增量获取人组关系
     * @param maxCustGroupVer
     * @param custGroupVer
     * @return
     */
    private List<CustGroupDto>  getAccessCustGroupList(Integer maxCustGroupVer, Integer custGroupVer){
        List<AccessCustGroupDto> accessCustGroupList = accessAuthService.getAccessCustGroupList(custGroupVer, maxCustGroupVer);
        List<CustGroupDto> custGroupList = new ArrayList<>();
        for (int i = 0; i < accessCustGroupList.size(); i++) {
            AccessCustGroupDto accessCustGroupDto = accessCustGroupList.get(i);
            String groupidArr = accessCustGroupDto.getGroup_id().toString();
            CustGroupDto custGroupDto = new CustGroupDto();
            custGroupDto.setGroup_id(getGroupHexStr(groupidArr));
            custGroupDto.setCust_code(accessCustGroupDto.getCust_code());
            custGroupDto.setStatus(accessCustGroupDto.getStatus());
            custGroupList.add(custGroupDto);
        }
        return custGroupList;
    }

    public Map<String, LinkedList<VisitorReplyDto>> getVisitorList(){
        Map<String, LinkedList<VisitorReplyDto>> visitorDevMap = new HashMap<>();
        List<AccessTemporaryCustDto> accessTemporaryCustList = accessAuthService.getTemporaryCustList();
        Map<String, List<AccessTemporaryCustDto>> devVistorMap = accessTemporaryCustList.stream().collect(Collectors.groupingBy(AccessTemporaryCustDto::getDev_sn, Collectors.toList()));
        for (String devSn : devVistorMap.keySet()) {
            String accessSn = devSn.substring(0, devSn.length() - 1);
            LinkedList<VisitorReplyDto> visitorReplyList = new LinkedList<>();
            List<AccessTemporaryCustDto> devTemporaryCustList = devVistorMap.get(devSn);
            List<VisitorDto> visitorList = new ArrayList<>();
            for (int i = 0; i < devTemporaryCustList.size(); i++) {
                VisitorDto visitorDto = new VisitorDto();
                AccessTemporaryCustDto accessTemporaryCustDto = devTemporaryCustList.get(i);
                visitorDto.setCard_no(accessTemporaryCustDto.getCard_no());
                visitorDto.setCust_code(accessTemporaryCustDto.getCust_code());
                visitorDto.setType(accessTemporaryCustDto.getType());
                visitorDto.setBegin_time(null == accessTemporaryCustDto.getBegin_time() ? 0: accessTemporaryCustDto.getBegin_time().getTime()/1000);
                visitorDto.setEnd_time(null == accessTemporaryCustDto.getEnd_time() ? 0: accessTemporaryCustDto.getEnd_time().getTime()/1000);
                visitorList.add(visitorDto);
            }
            int stepLength = 30;
            int frameNum = (int) Math.ceil(((double)visitorList.size())/stepLength);
            for (int k = 0; k < frameNum; k++) {
                VisitorReplyDto visitorReplyDto = new VisitorReplyDto();
                visitorReplyDto.setDevSn(devSn);
                visitorReplyDto.setSeq(AccessUtils.getSeq(k));
                DevVisitorDto devVisitorDto = new DevVisitorDto();
                devVisitorDto.setDev_sn(devSn);
                List<VisitorDto> subVisitorList = visitorList.subList(k*stepLength, (k+1)*stepLength < visitorList.size() ? (k+1)*stepLength : visitorList.size());
                devVisitorDto.setCust_arr(subVisitorList);
                devVisitorDto.setIs_end((k+1)*stepLength < visitorList.size() ? 0 : 1);
                visitorReplyDto.setDevVisitorDto(devVisitorDto);
                visitorReplyList.add(visitorReplyDto);
            }
            if(null != visitorDevMap.get(accessSn)){
                visitorDevMap.get(accessSn).addAll(visitorReplyList);
            }else{
                visitorDevMap.put(accessSn, visitorReplyList);
            }
        }
        return visitorDevMap;
    }

    /**
     * 获取16进制权限组
     * @param groupidArr
     * @return
     */
    private String getGroupHexStr(String groupidArr){
        Set<String> groupidSet = new HashSet<String>(Arrays.asList(groupidArr.split(",")));
        String result = "";
        for (int i = 1; i <= 128; i++) {
            result = (groupidSet.contains(String.valueOf(i)) ? "1" : "0") + result;
        }
        String resultHex = "";
        for (int i = 0; i < 32; i++) {
            resultHex += Integer.toHexString(Integer.parseInt(result.substring(i*4, (i+1)*4), 2));
        }
        return resultHex.toUpperCase();
    }
}
