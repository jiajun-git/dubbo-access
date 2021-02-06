package com.access.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccessRecordDto implements Serializable {
    //设备编号
    String dev_sn;
    //记录流水号
    String record_no;
    //物理卡号
    String card_no;
    //客户编码
    String cust_code;
    //记录类型 （0-刷卡 1-扫码 2-刷脸 3-二维码）
    Integer type;
    //记录时间
    Date time;
    //是否合法（0-合法 1-非法）
    Integer is_lawful;
    //电子校徽进出方向（1进，2出）
    Integer inout_status;
    //备注
    Integer remark;

}