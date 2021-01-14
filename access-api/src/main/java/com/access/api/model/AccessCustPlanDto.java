package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessCustPlanDto implements Serializable {
    Integer plan_id;	//是	number	客户计划id
    Integer week;	//是	number	周几（1-7）
    Integer begin_hour;	//是	string	开始小时
    Integer begin_minute;	//是	number	开始分钟
    Integer end_hour;	//是	number	结束小时
    Integer end_minute;	//是	number	结束分钟
    Integer type;
}