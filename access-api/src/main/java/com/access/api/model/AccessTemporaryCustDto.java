package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessTemporaryCustDto implements Serializable {
    //访客表id
    Integer id;
    //设备编号
    String devSn;
    //授权或取消授权（1-授权，2-取消授权）
    Integer type;
    //客户编码
    String custCode;
    //物理卡号
    String cardNo;
    //授权开始时间
    String beginTime;
    //授权结束时间
    String endTime;
    //开门方式
    Integer openType;
}