package com.danbro.config;

import com.danbro.interceptor.AllInterceptor;
import com.danbro.interceptor.LoginInterceptor;
import com.danbro.interceptor.LogoutInterceptor;
import com.danbro.resolvers.MyHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @Classname MyConfig
 * @Description TODO
 * @Date 2020/6/16 21:17
 * @Author Danrbo
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.danbro")
public class MyConfig implements WebMvcConfigurer {
	@Autowired
	LogoutInterceptor logoutInterceptor;

	@Autowired
	LoginInterceptor loginInterceptor;

	@Autowired
	AllInterceptor allInterceptor;
	@Autowired
	MyHandlerMethodArgumentResolver myHandlerMethodArgumentResolver;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/login");
		registry.addInterceptor(logoutInterceptor).addPathPatterns("/logout");
		registry.addInterceptor(allInterceptor).addPathPatterns("/*");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(myHandlerMethodArgumentResolver);
	}

	/**
	 * 配置视图解析器
	 * @return 视图解析器
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
