package com.springcloud.eurekaclient.enums;

import lombok.Getter;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 16:39 2019/11/25
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROR(2,"库存有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
