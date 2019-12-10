package com.springcloud.client;

import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 15:37 2019/11/22
 */
@FeignClient(name = "eureka-product")
public interface ProductClient {

    @PostMapping("product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @GetMapping("product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartVOList);
}