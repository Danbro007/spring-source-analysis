package com.danbro.resolvers;

import com.danbro.annotations.Danbro;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Classname MyHandlerMethodArgumentResolver
 * @Description TODO
 * @Date 2020/6/16 20:47
 * @Author Danrbo
 */
@Component
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(Danbro.class)) {
			Danbro danbro = parameter.getParameterAnnotation(Danbro.class);
			if (danbro != null && StringUtils.hasText(danbro.name())){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Danbro danbro = parameter.getParameterAnnotation(Danbro.class);
		String name = danbro.name();
		String parameter1 = webRequest.getParameter(name);
		System.out.println("this is myHandlerMethodArgumentResolver");
		return parameter1.toUpperCase();
	}
}
