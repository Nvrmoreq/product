package com.springcloud.eurekaclient.controller;

import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.eurekaclient.bean.ProductCategory;
import com.springcloud.eurekaclient.bean.ProductInfo;
import com.springcloud.eurekaclient.service.ProductSevice;
import com.springcloud.eurekaclient.vo.CartVO;
import com.springcloud.eurekaclient.vo.ProductInfoVO;
import com.springcloud.eurekaclient.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 11:46 2019/11/14
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductSevice productSevice;
    /**
     * 1.查询所有在架商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public List<ProductVO> list(){
        List<ProductInfo> productInfoList =  productSevice.findUpAll();
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productSevice.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return productVOList;
    }

    /**
     * 获取商品列表（给订单服务使用）
     * 注意：当参数使用@RequestBody时，请求一定要使用post，当无参或单参数、@RequestParam、@PathVariable时可以用get
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){
        return productSevice.findByProductIdIn(productIdList);
    }

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productSevice.decreaseStock(decreaseStockInputList);
    }
}
