package com.mashibing.common.exception;

import com.mashibing.common.enums.ExceptionEnums;
import lombok.Getter;

/**
 * 异常封装
 */
@Getter
public class ApiException extends RuntimeException {
    private Integer code;

    public ApiException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    public ApiException(ExceptionEnums enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }
}
