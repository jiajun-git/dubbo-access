package com.access.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: sjj
 * @description: 门禁控制器初始化参数
 * @create: 2021-01-08
 **/

@Data
public class AccessDevInitDto implements Serializable {
    Integer mac;
    String dev_sn;
}
