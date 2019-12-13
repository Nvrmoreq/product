package com.springcloud.eurekaclient.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.tools.json.JSONUtil;
import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.eurekaclient.bean.ProductCategory;
import com.springcloud.eurekaclient.bean.ProductInfo;
import com.springcloud.eurekaclient.constants.ProductConstants;
import com.springcloud.eurekaclient.dao.ProductCategoryDao;
import com.springcloud.eurekaclient.dao.ProductInfoDao;
import com.springcloud.eurekaclient.enums.ResultEnum;
import com.springcloud.eurekaclient.exception.ProductionException;
import com.springcloud.eurekaclient.form.JsonFrom;
import com.springcloud.eurekaclient.service.ProductSevice;
import com.springcloud.eurekaclient.vo.CartVO;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 14:05 2019/11/14
 */
@Service
public class ProductServiceImpl implements ProductSevice {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductConstants.UP);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProductInfoOutput> findByProductIdIn(List<String> productIdList) {
        return productInfoDao.findByProductIdIn(productIdList).stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e,output);
            return output;
        }).collect(Collectors.toList());
    }

    @Override

    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        //发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e,output);
            return output;
        }).collect(Collectors.toList());
        JsonFrom jsonFrom = new JsonFrom();
        jsonFrom.setProductInfoOutputList(productInfoOutputList);
        amqpTemplate.convertAndSend("productInfo", /*JSON.toJSON(productInfoOutput)*/JSONObject.fromObject(jsonFrom).toString());
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for(DecreaseStockInput decreaseStockInput: decreaseStockInputList){
            //判断商品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoDao.findById(decreaseStockInput.getProductId());
            if(!productInfoOptional.isPresent()){
                throw new ProductionException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
            if(result < 0){
                throw new ProductionException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
