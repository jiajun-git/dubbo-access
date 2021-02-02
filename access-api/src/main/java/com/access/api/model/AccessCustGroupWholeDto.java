package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessCustGroupWholeDto implements Serializable {
    //权限组编号组合
    String groupid_arr;
    //状态（1-新增 0-删除）
    //客户编码
    String cust_code;
}