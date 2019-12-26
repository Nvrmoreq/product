package com.springcloud.eurekaclient.dao;

import com.springcloud.eurekaclient.bean.ProductInfo;
import com.springcloud.eurekaclient.service.ProductSevice;
import com.springcloud.eurekaclient.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 13:46 2019/11/14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductSevice productSevice;

    @Test
    public void findByProductStatus() {
        /*List<ProductInfo> list =  productInfoDao.findByProductStatus(0);
        log.info(list.toString());*/
    }

    @Test
    public void findByProductIdIn() {
        /*List<ProductInfo> list =  productInfoDao.findByProductIdIn(Arrays.asList("157875196366160022","157875227953464068"));
        log.info(list.toString());*/
    }

    @Test
    public void decreaseStock() throws Exception {
        /*CartVO cartVO = new CartVO("157875196366160022",2);
        productSevice.decreaseStock(Arrays.asList(cartVO));*/
        //log.info();
    }
}