package com.access.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccessCustDto implements Serializable {
    //类型(0-取消授权 1-新增授权)
    Integer type;
    //客户编码
    String cust_code;
    //卡状态
    Integer card_status;
    //用户密码
    String consum_pwd;
    //物理卡号
    String card_no;
    //授权开始时间
    Date begin_time;
    //授权结束时间
    Date end_time;
    //是否为特权卡
    Integer is_special;
}