package com.springcloud.eurekaclient.dao;

import com.springcloud.eurekaclient.bean.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 13:55 2019/11/14
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    /*    List<ProductCategory> findProductCategoryByCategoryTypeIn(List<Integer> categoryTypeList);*/


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
