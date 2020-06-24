package com.danbro.config;

import com.danbro.interceptor.AllInterceptor;
import com.danbro.interceptor.LoginInterceptor;
import com.danbro.interceptor.LogoutInterceptor;
import com.danbro.other.StringToUserConverter;
import com.danbro.resolvers.MyHandlerMethodArgumentResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

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
public class MyConfig implements ApplicationContextAware, WebMvcConfigurer {

	private ApplicationContext applicationContext;


	public MyConfig() {
		super();
	}

	@Autowired
	LogoutInterceptor logoutInterceptor;

	@Autowired
	LoginInterceptor loginInterceptor;

	@Autowired
	AllInterceptor allInterceptor;

	@Autowired
	MyHandlerMethodArgumentResolver myHandlerMethodArgumentResolver;

	@Autowired
	StringToUserConverter stringToUserConverter;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(stringToUserConverter);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/login");
		registry.addInterceptor(logoutInterceptor).addPathPatterns("/*");
		registry.addInterceptor(allInterceptor).addPathPatterns("/*");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(myHandlerMethodArgumentResolver);
	}

	/**
	 * 配置视图解析器 解析 JSP 文件
	 *
	 * @return InternalResourceViewResolver
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * 配置 ThymeleafViewResolver
	 *
	 * @return ThymeleafViewResolver 视图解析器
	 */
	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(true);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}
}
