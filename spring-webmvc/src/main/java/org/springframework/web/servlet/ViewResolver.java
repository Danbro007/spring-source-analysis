/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.web.servlet;

import java.util.Locale;

import org.springframework.lang.Nullable;

/**
 * Interface to be implemented by objects that can resolve views by name.
 *
 * <p>View state doesn't change during the running of the application,
 * so implementations are free to cache views.
 *
 * <p>Implementations are encouraged to support internationalization,
 * i.e. localized view resolution.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see org.springframework.web.servlet.view.InternalResourceViewResolver
 * @see org.springframework.web.servlet.view.ResourceBundleViewResolver
 * @see org.springframework.web.servlet.view.XmlViewResolver
 */
public interface ViewResolver {

	/**
	 * Resolve the given view by name.
	 * <p>Note: To allow for ViewResolver chaining, a ViewResolver should
	 * return {@code null} if a view with the given name is not defined in it.
	 * However, this is not required: Some ViewResolvers will always attempt
	 * to build View objects with the given name, unable to return {@code null}
	 * (rather throwing an exception when View creation failed).
	 *
	 * 通过给的视图名解析。
	 * 注意：为了允许实现视图解析器链，如果给定的视图名没有被定义在视图解析器中则应该返回 null。
	 * 然而，一些视图解析器经常使用给定的视图名创建一个视图对象是不必要的，这样不会返回 null
	 * （而不是在创建视图失败时抛出异常）。
	 *
	 * @param viewName name of the view to resolve
	 * @param locale the Locale in which to resolve the view.
	 * ViewResolvers that support internationalization should respect this.
	 * @return the View object, or {@code null} if not found
	 * (optional, to allow for ViewResolver chaining)
	 * @throws Exception if the view cannot be resolved
	 * (typically in case of problems creating an actual View object)
	 */
	@Nullable
	View resolveViewName(String viewName, Locale locale) throws Exception;

}
