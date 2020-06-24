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

import javax.servlet.ServletException;

import org.springframework.util.Assert;

/**
 * Exception to be thrown on error conditions that should forward
 * to a specific view with a specific model.
 *
 * 在错误条件下抛出的异常应该将转发给具有特定模型的特定视图。
 *
 * <p>Can be thrown at any time during handler processing.
 * This includes any template methods of pre-built controllers.
 * For example, a form com.danbro.springmvc.controller might abort to a specific error page
 * if certain parameters do not allow to proceed with the normal workflow.
 *
 * 在处理器处理时的任意时间抛出异常。这包括任意 pre-built 控制器的任意模板方法。
 * 例如：如果某些参数不允许继续正常工作流程，控制器中的一个方法可以中止跳转到特定的错误页
 *
 * @author Juergen Hoeller
 * @since 22.11.2003
 */
@SuppressWarnings("serial")
public class ModelAndViewDefiningException extends ServletException {

	private final ModelAndView modelAndView;


	/**
	 * Create new ModelAndViewDefiningException with the given ModelAndView,
	 * typically representing a specific error page.
	 * @param modelAndView the ModelAndView with view to forward to and model to expose
	 */
	public ModelAndViewDefiningException(ModelAndView modelAndView) {
		Assert.notNull(modelAndView, "ModelAndView must not be null in ModelAndViewDefiningException");
		this.modelAndView = modelAndView;
	}

	/**
	 * Return the ModelAndView that this exception contains for forwarding to.
	 */
	public ModelAndView getModelAndView() {
		return this.modelAndView;
	}

}
