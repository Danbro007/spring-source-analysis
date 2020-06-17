package com.danbro.springLifeCycleAnalysis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Classname MyConfig
 * @Description TODO
 * @Date 2020/6/2 9:57
 * @Author Danrbo
 */
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.danbro.springLifeCycleAnalysis")
public class MyConfig {
}
