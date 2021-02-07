package com.access.communication.utils;

import cn.hutool.core.util.HexUtil;
import com.access.communication.netty.dto.DevReceiveDto;

public class AccessUtils {

    /**
     * 转换HEX字节长度，固定2字节，高字节在后，低字节在前
     * @param lengthHex
     * @return
     */
    public static int getLength(String lengthHex){
        String revertHex = lengthHex.substring(2, 4) + lengthHex.substring(0, 2);
        return Integer.valueOf(revertHex, 16);
    }

    /**
     * 转换HEX字节长度，固定2字节，高字节在后，低字节在前
     * @param length
     * @return
     */
    public static String getLengthStr(int length){
        String lengthHex = Integer.toHexString(length).toUpperCase();
        if(length <= 0xF){
            return "0" + lengthHex + "00";
        }else if(length <= 0xFF){
            return lengthHex + "00";
        }else if(length <= 0xFFF){
            return lengthHex.substring(1, 3) + "0" + lengthHex.substring(0, 1);
        }else if(length <= 0xFFFF){
            return lengthHex.substring(2, 4) + lengthHex.substring(0, 2);
        }else{
            return null;
        }
    }

    /**
     * 生成发送数据
     * @param data json数据
     * @param type 功能码
     * @param messageType 消息类型(00发送数据 01询问)
     * @return
     */
    public static String generateData(String data, String type, String messageType, String seq){
        String hexData = HexUtil.encodeHexStr(data).toUpperCase();
        String hexDataLengthStr = getLengthStr(hexData.length() / 2);
        String totalLength = getLengthStr(2 + 2 + 1 +1 + 2 + 1 + 1 + 2 + hexData.length() / 2 + 2);
        String headStr = totalLength + totalLength + messageType + "01";
        String headCRC16 = CrcUtils.getCRC16Modbus(headStr);
        String dataStr = headStr + headCRC16 + type + seq + hexDataLengthStr + hexData;
        String dataCRC16 = CrcUtils.getCRC16Modbus(dataStr);
        String response = "9E" + dataStr + dataCRC16 + "5E";
        return response;
    }

    /**
     * 获取帧序号
     * @param seq
     * @return
     */
    public static String getSeq(int seq){
        String seqHexStr = Integer.toHexString(seq%256).toUpperCase();
        if(seq%256 <= 0xF){
            return "0" + seqHexStr;
        }else{
            return seqHexStr;
        }
    }

    /**
     * 处理接收数据
     * @param content
     * @return
     */
    public static DevReceiveDto parseData(String content){
        //截取功能码
        String type = content.substring(18, 20);
        String dataLengthHex = content.substring(22, 26);
        int dataLength = AccessUtils.getLength(dataLengthHex);
        //截取json内容
        String data = content.substring(26, 26 + dataLength * 2);
        data = HexUtil.decodeHexStr(data);
        DevReceiveDto devReceiveDto = new DevReceiveDto();
        devReceiveDto.setType(type);
        devReceiveDto.setData(data);
        return devReceiveDto;
    }
}
