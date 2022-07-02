package com.slcp.entity;

import com.slcp.api.ResultCode;
import com.slcp.enums.ExceptionBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DevopsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DevopsException(ExceptionBaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.defaultMessage = baseEnum.getMessage();
    }

    public DevopsException(ResultCode resultCode, String details) {
        this.code = resultCode.getCode();
        this.defaultMessage = details;
        this.details = details;
    }


    /**
     * 错误码
     */
    private int code;

    /**
     * 错误消息
     */
    private String defaultMessage;

    /**
     * 消息详情描述
     */
    private String details;
}
