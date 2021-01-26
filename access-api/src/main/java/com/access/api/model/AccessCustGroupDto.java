package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessCustGroupDto implements Serializable {
    //权限组编号
    Integer group_id;
    //状态（1-新增 0-删除）
    Integer status;
    //客户编码
    String cust_code;
}