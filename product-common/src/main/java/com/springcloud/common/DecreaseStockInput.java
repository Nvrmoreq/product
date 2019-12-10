package com.springcloud.common;

import lombok.Data;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:34 2019/11/27
 */
@Data
public class DecreaseStockInput {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;


    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
