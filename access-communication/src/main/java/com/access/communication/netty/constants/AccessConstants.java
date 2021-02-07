package com.access.communication.netty.constants;

public final class AccessConstants {

    /**
     * 默认帧序号
     */
    public static final String DEFAULT_SEQ = "00";

    /**
     * 消息类型--发送数据
     */
    public static final String MSG_DATA = "00";

    /**
     * 消息类型--询问状态
     */
    public static final String MSG_ASK = "01";

    /**
     * 功能码--签到
     */
    public static final String FUNC_REGISTER = "02";

    /**
     * 功能码--心跳
     */
    public static final String FUNC_HEARTBEAT = "03";

    /**
     * 功能码--用户计划
     */
    public static final String FUNC_USERPLAN = "07";

    /**
     * 功能码--权限组
     */
    public static final String FUNC_GROUP = "08";

    /**
     * 功能码--用户名单
     */
    public static final String FUNC_CUST = "09";

    /**
     * 功能码--用户权限组
     */
    public static final String FUNC_CUST_GROUP = "0A";

    /**
     * 功能码--临时名单
     */
    public static final String FUNC_VISITOR = "0B";

    /**
     * 功能码--上报记录
     */
    public static final String FUNC_RECORD = "0C";

    /**
     * 功能码--流程结束
     */
    public static final String FUNC_END = "FF";

}
