package com.access.communication.utils;

public class HexUtils {

    /**
     * 2进制转16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuffer sb = null;
        try {
            sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String strHex = Integer.toHexString(bytes[i]);
                if (strHex.length() > 3) {
                    sb.append(strHex.substring(6));
                } else {
                    if (strHex.length() < 2) {
                        sb.append("0" + strHex);
                    } else {
                        sb.append(strHex);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (sb == null ? null : sb.toString().toUpperCase());
    }

}
