package com.danbro.springCircularReference;

import com.danbro.springCircularReference.myconfig.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/6/9 19:41
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
