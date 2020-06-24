/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that identifies methods which initialize the
 * {@link org.springframework.web.bind.WebDataBinder} which
 * will be used for populating command and form object arguments
 * of annotated handler methods.
 *
 * 标识初始化 org.springframework.web.bind的方法的注解。用于填充带注解的处理器方法的命令和表单对象参数。
 *
 * <p>Such init-binder methods support all arguments that {@link RequestMapping}
 * supports, except for command/form objects and corresponding validation result
 * objects. Init-binder methods must not have a return value; they are usually
 * declared as {@code void}.
 *
 * 这种 init-binder 方法支持  RequestMapping 支持的所有参数，除了 命令/表单 对象和相应的验证结果对象。
 * Init-binder 方法不能有返回值;它们通常声明为 void。
 *
 * <p>Typical arguments are {@link org.springframework.web.bind.WebDataBinder}
 * in combination with {@link org.springframework.web.context.request.WebRequest}
 * or {@link java.util.Locale}, allowing to register context-specific editors.
 *
 * 典型的参数是 WebDataBinder 与 WebRequest 或者 Locale 结合使用，允许注册 context 特定的编辑器。
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see org.springframework.web.bind.WebDataBinder
 * @see org.springframework.web.context.request.WebRequest
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InitBinder {

	/**
	 * The names of command/form attributes and/or request parameters
	 * that this init-binder method is supposed to apply to.
	 * <p>Default is to apply to all command/form attributes and all request parameters
	 * processed by the annotated handler class. Specifying model attribute names or
	 * request parameter names here restricts the init-binder method to those specific
	 * attributes/parameters, with different init-binder methods typically applying to
	 * different groups of attributes or parameters.
	 *
	 * 这个 init-binder 方法应该应用于的 命令/表单 属性 和/或 请求参数的名。
	 * 默认是适用于所有的 命令/表单 属性 和所有通过被注解的处理器类处理的请求参数。
	 * 在这里指定模型属性名或请求参数名将初始绑定方法限制为那些特定的 属性/参数，
	 * 不同的初始绑定方法通常应用于不同的属性或参数组。
	 */
	String[] value() default {};

}
