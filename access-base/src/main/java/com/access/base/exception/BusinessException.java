package com.access.base.exception;

import lombok.Data;

/**
 * @description: 业务处理处理异常
 * @author: sjj
 * @create: 2021-01-12 11:16
 **/
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3313503448905936913L;
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    public BusinessException(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
