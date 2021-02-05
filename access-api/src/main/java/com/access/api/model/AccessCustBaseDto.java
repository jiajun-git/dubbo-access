package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessCustBaseDto implements Serializable {
    //客户id
    Integer custId;
    //客户姓名
    String custName;
    //部门id
    Integer deptId;
    //部门名称
    String deptName;
    //学工号
    String workNo;
    //部门编码
    String deptCode;
}