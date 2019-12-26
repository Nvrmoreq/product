package com.springcloud.eurekaclient.exception;

import com.springcloud.eurekaclient.enums.ResultEnum;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 16:33 2019/11/25
 */
public class ProductionException extends RuntimeException {

    private Integer code;

    private String message;

    public ProductionException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
