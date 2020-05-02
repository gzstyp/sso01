package com.fwtai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 授权服务
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-02 12:45
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Configuration
@EnableAuthorizationServer//有了该注解就是标准的sso的认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    //配置1
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
            .withClient("web1")//项目web1的配置,用于客户端的配置 security.oauth2.client.client-id=web1
            .secret("secret1")//security.oauth2.client.client-secret=secret1
            .authorizedGrantTypes("authorization_code","refresh_token")//提供两种认证方式(由BaseClientDetails提供)
            .scopes("all")
            .and()//添加第2个客户端
            .withClient("web2")//项目web2的配置,用于客户端的配置 security.oauth2.client.client-id=web1
            .secret("secret2")//security.oauth2.client.client-secret=secret1
            .authorizedGrantTypes("authorization_code","refresh_token")//提供两种认证方式(由BaseClientDetails提供)
            .scopes("all");
    }

    //配置2，结合并生成令牌()
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessToken());
    }

    //配置3,认证服务器的安全配置
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception{
        security.tokenKeyAccess("isAuthenticated()");//是security认证表达式(表示f访问前面的 tokenKeyAccess 都要认证，key就是下面的'www.yinlz.com')
    }

    //发令牌
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessToken());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessToken(){
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("www.yinlz.com");
        return converter;
    }
}

































