package com.springcloud.eurekaclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 15:57 2019/11/22
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return " this is a product' msg ";
    }
}
