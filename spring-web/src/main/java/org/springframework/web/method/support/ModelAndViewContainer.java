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

package org.springframework.web.method.support;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;

/**
 * Records model and view related decisions made by
 * {@link HandlerMethodArgumentResolver HandlerMethodArgumentResolvers} and
 * {@link HandlerMethodReturnValueHandler HandlerMethodReturnValueHandlers} during the course of invocation of
 * a com.danbro.springmvc.controller method.
 *
 * 记录在调用一个 controller 方法过程中通过参数解析器和返回值处理器做的与 ModelAndView 相关的决定
 *
 * <p>The {@link #setRequestHandled} flag can be used to indicate the request
 * has been handled directly and view resolution is not required.
 *
 * setRequestHandled() 标记被用来表明当前请求已经被直接处理，不需要视图解析。
 *
 * <p>A default {@link Model} is automatically created at instantiation.
 * An alternate model instance may be provided via {@link #setRedirectModel}
 * for use in a redirect scenario. When {@link #setRedirectModelScenario} is set
 * to {@code true} signalling a redirect scenario, the {@link #getModel()}
 * returns the redirect model instead of the default model.
 *
 * 一个默认模型在实例化时能被自动创建。可以通过 setRedirectModel() 提供另一个用于重定向场景的模型实例。
 * 当 setRedirectModelScenario() 被设置为 true 时表示这是一个重定向场景，这时使用 getModel() 方法会返回一个重定向模型代替默认的模型。
 *
 * @author Rossen Stoyanchev
 * @author Juergen Hoeller
 * @since 3.1
 */
public class ModelAndViewContainer {

	private boolean ignoreDefaultModelOnRedirect = false;

	@Nullable
	private Object view;
	// 默认模型 是 BindingAwareModelMap 对象
	private final ModelMap defaultModel = new BindingAwareModelMap();

	@Nullable
	private ModelMap redirectModel;
	// 是否是重定向模型 默认为 false ，通过 setRedirectModelScenario() 修改为 true
	private boolean redirectModelScenario = false;

	// 状态码
	@Nullable
	private HttpStatus status;

	private final Set<String> noBinding = new HashSet<>(4);

	private final Set<String> bindingDisabled = new HashSet<>(4);

	private final SessionStatus sessionStatus = new SimpleSessionStatus();

	private boolean requestHandled = false;


	/**
	 * By default the content of the "default" model is used both during
	 * rendering and redirect scenarios. Alternatively com.danbro.springmvc.controller methods
	 * can declare an argument of type {@code RedirectAttributes} and use
	 * it to provide attributes to prepare the redirect URL.
	 * <p>Setting this flag to {@code true} guarantees the "default" model is
	 * never used in a redirect scenario even if a RedirectAttributes argument
	 * is not declared. Setting it to {@code false} means the "default" model
	 * may be used in a redirect if the com.danbro.springmvc.controller method doesn't declare a
	 * RedirectAttributes argument.
	 *
	 * 在渲染和重定向场景中使用的默认模型的内容。另外 controller 的方法能声明一个类型为 RedirectAttributes 的参数
	 * 并且使用它提供来准备重定向的 URl 属性。
	 *
	 * 设置 ignoreDefaultModelOnRedirect 这个标志位为 true 保证默认模型永远不会在重定向场景中使用，即使 RedirectAttributes 参数没有被声明。
	 * 把它设置为 false 意味着默认模式可以在重定向中使用如果 controller 方法没有声明一个
	 * RedirectAttributes 参数。
	 *
	 * <p>The default setting is {@code false}.
	 *
	 * 默认是 false
	 *
	 */
	public void setIgnoreDefaultModelOnRedirect(boolean ignoreDefaultModelOnRedirect) {
		this.ignoreDefaultModelOnRedirect = ignoreDefaultModelOnRedirect;
	}

	/**
	 * Set a view name to be resolved by the DispatcherServlet via a ViewResolver.
	 * Will override any pre-existing view name or View.
	 */
	public void setViewName(@Nullable String viewName) {
		this.view = viewName;
	}

	/**
	 * Return the view name to be resolved by the DispatcherServlet via a
	 * ViewResolver, or {@code null} if a View object is set.
	 */
	@Nullable
	public String getViewName() {
		return (this.view instanceof String ? (String) this.view : null);
	}

	/**
	 * Set a View object to be used by the DispatcherServlet.
	 * Will override any pre-existing view name or View.
	 */
	public void setView(@Nullable Object view) {
		this.view = view;
	}

	/**
	 * Return the View object, or {@code null} if we using a view name
	 * to be resolved by the DispatcherServlet via a ViewResolver.
	 *
	 * 返回视图对象，或者 null，如果我们使用视图名 由 DispatcherServlet 通过视图解析器解析。
	 *
	 */
	@Nullable
	public Object getView() {
		return this.view;
	}

	/**
	 * Whether the view is a view reference specified via a name to be
	 * resolved by the DispatcherServlet via a ViewResolver.
	 *
	 * 视图对象是否是通过名称指定的视图引用，由 DispatcherServlet 通过视图解析器解析。
	 */
	public boolean isViewReference() {
		return (this.view instanceof String);
	}

	/**
	 * Return the model to use -- either the "default" or the "redirect" model.
	 * The default model is used if {@code redirectModelScenario=false} or
	 * there is no redirect model (i.e. RedirectAttributes was not declared as
	 * a method argument) and {@code ignoreDefaultModelOnRedirect=false}.
	 *
	 * 返回使用的模型，默认模型或者重定向模型。如果 redirectModelScenario=false
	 * 或者没有重定向模型(RedirectAttributes 没有声明为一个方法参数并且 ignoreDefaultModelOnRedirect=false)
	 * 则使用默认模型。
	 *
	 */
	public ModelMap getModel() {
		if (useDefaultModel()) {
			return this.defaultModel;
		}
		else {
			if (this.redirectModel == null) {
				this.redirectModel = new ModelMap();
			}
			return this.redirectModel;
		}
	}

	/**
	 * Whether to use the default model or the redirect model.
	 */
	private boolean useDefaultModel() {
		return (!this.redirectModelScenario || (this.redirectModel == null && !this.ignoreDefaultModelOnRedirect));
	}

	/**
	 * Return the "default" model created at instantiation.
	 * <p>In general it is recommended to use {@link #getModel()} instead which
	 * returns either the "default" model (template rendering) or the "redirect"
	 * model (redirect URL preparation). Use of this method may be needed for
	 * advanced cases when access to the "default" model is needed regardless,
	 * e.g. to save model attributes specified via {@code @SessionAttributes}.
	 *
	 * 在实例化时返回一个默认模型。一般来说推荐使用 getModel() 来替代返回的默认模型（渲染模板）
	 * 或者重定向模型（用来准备重定向 URL）。在高级情况下可能需要通过 @SessionAttributes
	 * 保存指定的模型属性的方法来访问默认模型。
	 *
	 * @return the default model (never {@code null})
	 * @since 4.1.4
	 */
	public ModelMap getDefaultModel() {
		return this.defaultModel;
	}

	/**
	 * Provide a separate model instance to use in a redirect scenario.
	 * <p>The provided additional model however is not used unless
	 * {@link #setRedirectModelScenario} gets set to {@code true}
	 * to signal an actual redirect scenario.
	 *
	 * 在使用重定向场景下提供一个单独的模型实例。
	 * 但是，除非 setRedirectModelScenario() 被设置为 true 以表示一个实际的重定向场景，否则不会使用所提供的附加模型。
	 *
	 */
	public void setRedirectModel(ModelMap redirectModel) {
		this.redirectModel = redirectModel;
	}

	/**
	 * Whether the com.danbro.springmvc.controller has returned a redirect instruction, e.g. a
	 * "redirect:" prefixed view name, a RedirectView instance, etc.
	 *
	 * 当 controller 已经返回一个重定向指令。例如：以 "redirect:"  开头的视图名，表示是一个重定向视图的实例。
	 *
	 */
	public void setRedirectModelScenario(boolean redirectModelScenario) {
		this.redirectModelScenario = redirectModelScenario;
	}

	/**
	 * Provide an HTTP status that will be passed on to with the
	 * {@code ModelAndView} used for view rendering purposes.
	 * @since 4.3
	 */
	public void setStatus(@Nullable HttpStatus status) {
		this.status = status;
	}

	/**
	 * Return the configured HTTP status, if any.
	 * @since 4.3
	 */
	@Nullable
	public HttpStatus getStatus() {
		return this.status;
	}

	/**
	 * Programmatically register an attribute for which data binding should not occur,
	 * not even for a subsequent {@code @ModelAttribute} declaration.
	 *
	 * 以编程的方式注册一个不需要绑定的属性，即使这个属性后面用 @ModelAttribute 注解的也不行。
	 *
	 * @param attributeName the name of the attribute
	 * @since 4.3
	 */
	public void setBindingDisabled(String attributeName) {
		this.bindingDisabled.add(attributeName);
	}

	/**
	 * Whether binding is disabled for the given model attribute.
	 *
	 * 是否对给定的模型属性解除绑定
	 *
	 * @since 4.3
	 */
	public boolean isBindingDisabled(String name) {
		return (this.bindingDisabled.contains(name) || this.noBinding.contains(name));
	}

	/**
	 * Register whether data binding should occur for a corresponding model attribute,
	 * corresponding to an {@code @ModelAttribute(binding=true/false)} declaration.
	 * <p>Note: While this flag will be taken into account by {@link #isBindingDisabled},
	 * a hard {@link #setBindingDisabled} declaration will always override it.
	 *
	 * 注解 @ModelAttribute(binding=true/false) 表明是否应该对相应的模型属性进行数据绑定，
	 * 注意：虽然这个标志会被 isBindingDisabled 考虑在内。
	 * 如果一个属性同时被 setBindingDisabled() 和 @ModelAttribute 作用，
	 * 则这个属性不会被绑定，既让 @ModelAttribute 失效。
	 *
	 * @param attributeName the name of the attribute
	 * @since 4.3.13
	 */
	public void setBinding(String attributeName, boolean enabled) {
		if (!enabled) {
			this.noBinding.add(attributeName);
		}
		else {
			this.noBinding.remove(attributeName);
		}
	}

	/**
	 * Return the {@link SessionStatus} instance to use that can be used to
	 * signal that session processing is complete.
	 *
	 * 返回 SessionStatus 实例，这个实例能表明会话处理已完成
	 *
	 */
	public SessionStatus getSessionStatus() {
		return this.sessionStatus;
	}

	/**
	 * Whether the request has been handled fully within the handler, e.g.
	 * {@code @ResponseBody} method, and therefore view resolution is not
	 * necessary. This flag can also be set when com.danbro.springmvc.controller methods declare an
	 * argument of type {@code ServletResponse} or {@code OutputStream}).
	 * <p>The default value is {@code false}.
	 *
	 * 请求是否已经被处理器完整处理。例如：@ResponseBody 方法，因此视图解析就不是很必要。当 controller 方法声明一个
	 * ServletResponse 或者 OutputStream 类型参数时能设置这个标志。
	 *
	 */
	public void setRequestHandled(boolean requestHandled) {
		this.requestHandled = requestHandled;
	}

	/**
	 * Whether the request has been handled fully within the handler.
	 */
	public boolean isRequestHandled() {
		return this.requestHandled;
	}

	/**
	 * Add the supplied attribute to the underlying model.
	 * A shortcut for {@code getModel().addAttribute(String, Object)}.
	 */
	public ModelAndViewContainer addAttribute(String name, @Nullable Object value) {
		getModel().addAttribute(name, value);
		return this;
	}

	/**
	 * Add the supplied attribute to the underlying model.
	 * A shortcut for {@code getModel().addAttribute(Object)}.
	 */
	public ModelAndViewContainer addAttribute(Object value) {
		getModel().addAttribute(value);
		return this;
	}

	/**
	 * Copy all attributes to the underlying model.
	 * A shortcut for {@code getModel().addAllAttributes(Map)}.
	 */
	public ModelAndViewContainer addAllAttributes(@Nullable Map<String, ?> attributes) {
		getModel().addAllAttributes(attributes);
		return this;
	}

	/**
	 * Copy attributes in the supplied {@code Map} with existing objects of
	 * the same name taking precedence (i.e. not getting replaced).
	 * A shortcut for {@code getModel().mergeAttributes(Map<String, ?>)}.
	 */
	public ModelAndViewContainer mergeAttributes(@Nullable Map<String, ?> attributes) {
		getModel().mergeAttributes(attributes);
		return this;
	}

	/**
	 * Remove the given attributes from the model.
	 */
	public ModelAndViewContainer removeAttributes(@Nullable Map<String, ?> attributes) {
		if (attributes != null) {
			for (String key : attributes.keySet()) {
				getModel().remove(key);
			}
		}
		return this;
	}

	/**
	 * Whether the underlying model contains the given attribute name.
	 * A shortcut for {@code getModel().containsAttribute(String)}.
	 */
	public boolean containsAttribute(String name) {
		return getModel().containsAttribute(name);
	}


	/**
	 * Return diagnostic information.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ModelAndViewContainer: ");
		if (!isRequestHandled()) {
			if (isViewReference()) {
				sb.append("reference to view with name '").append(this.view).append("'");
			}
			else {
				sb.append("View is [").append(this.view).append(']');
			}
			if (useDefaultModel()) {
				sb.append("; default model ");
			}
			else {
				sb.append("; redirect model ");
			}
			sb.append(getModel());
		}
		else {
			sb.append("Request handled directly");
		}
		return sb.toString();
	}

}
