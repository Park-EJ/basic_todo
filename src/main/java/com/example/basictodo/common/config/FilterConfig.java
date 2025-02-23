package com.example.basictodo.common.config;

import com.example.basictodo.common.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    // 모든 요청에 대해 LoginFilter를 거치도록 설정하는 역할

    @Bean
    public FilterRegistrationBean<Filter> loginFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter()); // 우리가 만든 LoginFilter를 적용합니다.
        registrationBean.setOrder(1); // 필터의 실행 순서를 지정합니다. (숫자가 낮을수록 먼저 실행됨)
        registrationBean.addUrlPatterns("/*"); // 모든 요청(/*)에 대해 필터를 적용합니다.
        return registrationBean;
    }

}