package com.access.communication.netty.dto.custgroup;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustGroupDto implements Serializable {

    private String cust_code;

    private String group_id;

    private Integer status;

}
