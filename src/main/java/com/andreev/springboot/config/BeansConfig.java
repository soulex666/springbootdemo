package com.andreev.springboot.config;

import com.andreev.springboot.beans.FirstBean;
import com.andreev.springboot.beans.ThirdBean;
import com.andreev.springboot.beans.ThirdBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public FirstBean firstBean() {
        return new FirstBean();
    }

    @Bean
    public FirstBean secondBean() {
        return new FirstBean("Vladimir", 37);
    }

    @Bean
    public ThirdBean thirdBean() {
        return new ThirdBeanImpl();
    }
}
