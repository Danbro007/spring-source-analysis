package com.danbro.springAopAnalysis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Classname AppConfig
 * @Description TODO
 * @Date 2020/6/8 11:51
 * @Author Danrbo
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.danbro.springAopAnalysis")
public class AppConfig {
}
