package com.fwtai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 配置WebSecurity
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-02 15:31
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //重写使用自己自定义的登录认证器及密码器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //处理静态资源或忽略的接口,如果不配置这里那需要把 注释的 //.mvcMatchers("/login.html").permitAll() 取消即可
    @Override
    public void configure(final WebSecurity web) throws Exception{
        final String[] IGNORE_URLS = {"/login.html","/user/register"};
        web.ignoring().antMatchers(IGNORE_URLS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/user/login")//这个是不需要配置为放行!!!
            .and()
            .authorizeRequests()
            //.mvcMatchers("/login.html").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable();
    }
}