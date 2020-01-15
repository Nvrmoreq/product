package com.springcloud.client;

import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 15:37 2019/11/22
 */
@FeignClient(name = "eureka-product",fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @PostMapping("product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @GetMapping("product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartVOList);

    @Component
    public static class ProductClientFallback implements ProductClient{

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> cartVOList) {

        }
    }
}