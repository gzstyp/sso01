package com.fwtai.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-02 14:10
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@RestController
@RequestMapping
public class ClientController{

    // http://127.0.0.1:8081/web1/user 打印同意认证并授权后的认证信息
    @GetMapping("/user")
    public Authentication user(final Authentication authentication){
        return authentication;
    }
}