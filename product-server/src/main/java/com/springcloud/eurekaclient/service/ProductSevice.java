package com.springcloud.eurekaclient.service;

import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.eurekaclient.bean.ProductCategory;

import com.springcloud.eurekaclient.bean.ProductInfo;
import com.springcloud.eurekaclient.vo.CartVO;


import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 14:03 2019/11/14
 */
public interface ProductSevice {

    /**
     * 查询所有商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 获取类目type列表
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findByProductIdIn(List<String> productIdList);

    /**
     * 扣库存
     * @return
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
