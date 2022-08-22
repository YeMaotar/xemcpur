package com.bill.xemcpur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 *
 * @author: zhangzhw
 * @Description:
 * @Date: Created in 13:46 2022/6/21
 * @Modified By:
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory)
    {
        RestTemplate rt =  new RestTemplate();
        //rt.getMessageConverters().add(new EcgMappingJackson2HttpMessageConverter());
        rt.setRequestFactory(factory);
        return rt;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }

}
