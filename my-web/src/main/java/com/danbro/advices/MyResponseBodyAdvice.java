package com.danbro.advices;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Classname MyResponseBodyAdvice
 * @Description TODO 响应增强器
 * @Date 2020/6/22 11:34
 * @Author Danrbo
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
				returnType.hasMethodAnnotation(ResponseBody.class));
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		System.out.println("----------MyResponseBodyAdvice-------");
		return body;
	}
}
