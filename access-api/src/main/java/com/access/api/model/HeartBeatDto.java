package com.access.api.model;


import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName HeartBeatDto
 * @Description: 设备心跳
 * @Author sjj
 * @Date 2021/01/12
 **/
@Data
public class HeartBeatDto implements Serializable {

    //人员版本号
    private int custVer;
    //权限组版本号
    private int groupVer;
    //人组关系版本号
    private int custgroupVer;
    //用户计划版本号
    private int custplanVer;
    //门禁计划版本号
    private int aceplanVer;
    //参数版本
    private int paramVer;

}
