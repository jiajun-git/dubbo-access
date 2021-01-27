package com.access.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccessCustDto implements Serializable {
    //类型(0-取消授权 1-新增授权)
    Integer t;
    //客户编码
    String cc;
    //卡状态
    Integer cs;
    //用户密码
    String cp;
    //物理卡号
    String cn;
    //授权开始时间
    Date bt;
    //授权结束时间
    Date et;
    //是否为特权卡
    Integer i;
}