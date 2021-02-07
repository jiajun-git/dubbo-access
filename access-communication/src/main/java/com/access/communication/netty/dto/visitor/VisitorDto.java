package com.access.communication.netty.dto.visitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisitorDto implements Serializable {

    private Integer type;

    private String cust_code;

    private String card_no;

    private Long begin_time;

    private Long end_time;

}
