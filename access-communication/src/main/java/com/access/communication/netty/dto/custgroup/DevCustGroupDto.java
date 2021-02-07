package com.access.communication.netty.dto.custgroup;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DevCustGroupDto implements Serializable {

    private String dev_sn;

    private Integer is_end;

    private Integer max_ver;

    private List<CustGroupDto> access_arr;
}
