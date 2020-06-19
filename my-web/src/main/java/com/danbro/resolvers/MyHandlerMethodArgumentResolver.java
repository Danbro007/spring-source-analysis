package com.danbro.resolvers;

import com.danbro.annotations.CatchArg;
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
		if (parameter.hasParameterAnnotation(CatchArg.class)) {
			CatchArg catchArg = parameter.getParameterAnnotation(CatchArg.class);
			if (catchArg != null && StringUtils.hasText(catchArg.name())){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		CatchArg catchArg = parameter.getParameterAnnotation(CatchArg.class);
		String name = catchArg.name();
		String parameter1 = webRequest.getParameter(name);
		System.out.println("this is myHandlerMethodArgumentResolver");
		return parameter1.toUpperCase();
	}
}
