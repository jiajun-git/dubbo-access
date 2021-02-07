package com.access.communication.utils;

import cn.hutool.core.io.checksum.crc16.CRC16Modbus;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CrcUtils {

    /**
     * 数据格式验证
     * @param content
     * @return
     */
    public static boolean validateData(String content){
        //CRC16校验
        String headStr = content.substring(2, 14);
        String headCRC16Str = content.substring(14, 18);
        if(!checkCRC16Modbus(headStr, headCRC16Str)){
            log.error("帧头【{}，{}】CRC校验失败", headStr, headCRC16Str);
            return false;
        }
        String dataStr = content.substring(2, content.length() -6);
        String dataCRC16Str = content.substring(content.length() -6 , content.length() -2);
        if(!checkCRC16Modbus(dataStr, dataCRC16Str)){
            log.error("数据【{}，{}】CRC校验失败", dataStr, dataCRC16Str);
            return false;
        }
        return true;
    }

    /**
     * CRC16Modbus校验
     * @param content 加密原始字符串
     * @param crc16Str 待比对校验串
     * @return
     */
    private static boolean checkCRC16Modbus(String content, String crc16Str){
        CRC16Modbus crc16Modbus = new CRC16Modbus();
        crc16Modbus.update(HexUtil.decodeHex(content));
        String checkStr = crc16Modbus.getHexValue();
        checkStr = StrUtil.padPre(checkStr, 4, "0").toUpperCase();
        return StringUtils.equalsIgnoreCase(checkStr, crc16Str);
    }

    /**
     * CRC16Modbus加密
     * @param content 加密原始字符串
     * @return
     */
    public static String getCRC16Modbus(String content){
        CRC16Modbus crc16Modbus = new CRC16Modbus();
        try{
            crc16Modbus.update(HexUtil.decodeHex(content));
        }catch (Exception e){
            System.out.println(content);
            e.printStackTrace();
        }
        String checkStr = crc16Modbus.getHexValue();
        checkStr = StrUtil.padPre(checkStr, 4, "0").toUpperCase();
        return checkStr;
    }

}
