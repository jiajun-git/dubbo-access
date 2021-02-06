package com.access.communication.netty.dto.heartbeat;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HeartbeatReqDto implements Serializable {

    /**
     * 门禁控制器编号
     */
    private String devSn;

    /**
     * 对应门禁信息
     */
    private List<DoorVerDto> door_array;

    /**
     * 控制器程序版本号
     */
    private Integer access_ver;

    /**
     * 人员版本号
     */
    private Integer cust_ver;

    /**
     * 人组关系版本号
     */
    private Integer custgroup_ver;

}
