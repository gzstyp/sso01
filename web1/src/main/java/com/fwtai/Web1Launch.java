package com.fwtai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-02 12:41
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@SpringBootApplication
@EnableOAuth2Sso
//@EnableOAuth2Client
public class Web1Launch{

    // http://127.0.0.1:8081/web1/index.html,访问它会自动跳转到认证服务器的进行登录认证
    public static void main(String[] args){
        SpringApplication.run(Web1Launch.class,args);
    }
}