package com.security.controller.config;

import com.security.controller.filter.TimeFilter;
import com.security.controller.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //ToDO need uncomment
//        registry.addInterceptor(timeInterceptor);
    }

    //ToDO need uncomment
//    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        TimeFilter filter = new TimeFilter();
        bean.setFilter(filter);
        List<String> url = new ArrayList<>();
        url.add("/*");
        bean.setUrlPatterns(url);
        return bean;
    }
}
