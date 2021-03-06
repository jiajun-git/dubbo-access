package com.access.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccessTemporaryCustDto implements Serializable {
    //访客表id
    Integer id;
    //设备编号
    String dev_sn;
    //授权或取消授权（1-授权，2-取消授权）
    Integer type;
    //客户编码
    String cust_code;
    //物理卡号
    String card_no;
    //授权开始时间
    Date begin_time;
    //授权结束时间
    Date end_time;
}