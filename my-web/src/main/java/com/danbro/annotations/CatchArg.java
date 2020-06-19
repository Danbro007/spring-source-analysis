package com.danbro.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname Danbro
 * @Description TODO
 * @Date 2020/6/16 20:45
 * @Author Danrbo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CatchArg {
	@AliasFor("name")
	String value() default "";

	@AliasFor("value")
	String name() default "";
}
