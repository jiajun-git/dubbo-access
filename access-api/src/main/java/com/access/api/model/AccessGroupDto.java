package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessGroupDto implements Serializable {
    //权限组编号
    Integer group_id;
    //开门方式
    Integer open_type;
    //用户计划id
    Integer plan_id;
}