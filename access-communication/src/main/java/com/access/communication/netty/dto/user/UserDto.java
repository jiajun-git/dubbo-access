package com.access.communication.netty.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Integer t;

    private String cc;

    private String cn;

    private Long bt;

    private Long et;

    private Integer cs;

    private String cp;

    private Integer is;

}
