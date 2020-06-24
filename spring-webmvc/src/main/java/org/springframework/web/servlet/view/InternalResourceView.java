/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.web.servlet.view;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

/**
 * Wrapper for a JSP or other resource within the same web application.
 * Exposes model objects as request attributes and forwards the request to
 * the specified resource URL using a {@link javax.servlet.RequestDispatcher}.
 *
 * 同一 web 应用程序中的 JSP 或其他资源的包装类。将模型对象作为请求属性公开，并使用请求分发器将请求转发给指定的资源 URL。
 *
 * <p>A URL for this view is supposed to specify a resource within the web
 * application, suitable for RequestDispatcher's {@code forward} or
 * {@code include} method.
 *
 * 此视图的 URL 应该指定 web 应用程序中的资源，适合请求分发器的 forward() 或 include() 方法。
 *
 * <p>If operating within an already included request or within a response that
 * has already been committed, this view will fall back to an include instead of
 * a forward. This can be enforced by calling {@code response.flushBuffer()}
 * (which will commit the response) before rendering the view.
 *
 * 如果在已包含的请求或已提交的响应中操作，这个视图将退回到 include() 而不是 forward()。在渲染视图对象前通过调用
 * response.flushBuffer()（这将会提交响应）来实现
 *
 * <p>Typical usage with {@link InternalResourceViewResolver} looks as follows,
 * from the perspective of the DispatcherServlet context definition:
 *
 * InternalResourceViewResolver 的典型用法如下，从 DispatcherServlet 上下文定义的角度来看：
 *
 * <pre class="code">&lt;bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver"&gt;
 *   &lt;property name="prefix" value="/WEB-INF/jsp/"/&gt;
 *   &lt;property name="suffix" value=".jsp"/&gt;
 * &lt;/bean&gt;</pre>
 *
 * Every view name returned from a handler will be translated to a JSP
 * resource (for example: "myView" -> "/WEB-INF/jsp/myView.jsp"), using
 * this view class by default.
 *
 * 从处理器返回的每个视图名将被转换为 JSP 资源（例如："myView" -> "/WEB-INF/jsp/myView.jsp"）默认使用这个视图类。
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @see javax.servlet.RequestDispatcher#forward
 * @see javax.servlet.RequestDispatcher#include
 * @see javax.servlet.ServletResponse#flushBuffer
 * @see InternalResourceViewResolver
 * @see JstlView
 */
public class InternalResourceView extends AbstractUrlBasedView {

	private boolean alwaysInclude = false;

	private boolean preventDispatchLoop = false;


	/**
	 * Constructor for use as a bean.
	 * @see #setUrl
	 * @see #setAlwaysInclude
	 */
	public InternalResourceView() {
	}

	/**
	 * Create a new InternalResourceView with the given URL.
	 * @param url the URL to forward to
	 * @see #setAlwaysInclude
	 */
	public InternalResourceView(String url) {
		super(url);
	}

	/**
	 * Create a new InternalResourceView with the given URL.
	 * @param url the URL to forward to
	 * @param alwaysInclude whether to always include the view rather than forward to it
	 */
	public InternalResourceView(String url, boolean alwaysInclude) {
		super(url);
		this.alwaysInclude = alwaysInclude;
	}


	/**
	 * Specify whether to always include the view rather than forward to it.
	 * <p>Default is "false". Switch this flag on to enforce the use of a
	 * Servlet include, even if a forward would be possible.
	 * @see javax.servlet.RequestDispatcher#forward
	 * @see javax.servlet.RequestDispatcher#include
	 * @see #useInclude(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void setAlwaysInclude(boolean alwaysInclude) {
		this.alwaysInclude = alwaysInclude;
	}

	/**
	 * Set whether to explicitly prevent dispatching back to the
	 * current handler path.
	 * <p>Default is "false". Switch this to "true" for convention-based
	 * views where a dispatch back to the current handler path is a
	 * definitive error.
	 */
	public void setPreventDispatchLoop(boolean preventDispatchLoop) {
		this.preventDispatchLoop = preventDispatchLoop;
	}

	/**
	 * An ApplicationContext is not strictly required for InternalResourceView.
	 */
	@Override
	protected boolean isContextRequired() {
		return false;
	}
	/**
	 * Render the internal resource given the specified model.
	 * This includes setting the model as request attributes.
	 *
	 * 渲染给定的模型的 JSP 。这包括将模型设置为请求属性。
	 */
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Expose the model object as request attributes. 、
		// 把模型里的所有属性放入请求里
		exposeModelAsRequestAttributes(model, request);

		// Expose helpers as request attributes, if any.
		// 公开渲染方式的 helper
		exposeHelpers(request);

		// Determine the path for the request dispatcher.
		// 渲染的jsp文件路径
		String dispatcherPath = prepareForRendering(request, response);

		// Obtain a RequestDispatcher for the target resource (typically a JSP).
		// 从目标资源那里获取一个请求分发器（通常是从 JSP 那里获取）
		RequestDispatcher rd = getRequestDispatcher(request, dispatcherPath);
		if (rd == null) {
			throw new ServletException("Could not get RequestDispatcher for [" + getUrl() +
					"]: Check that the corresponding file exists within your web application archive!");
		}

		// If already included or response already committed, perform include, else forward.
		// 如果 alwaysInclude 属性是 true 或者响应已经被提交会使用 include() 其他情况使用 forward()
		if (useInclude(request, response)) {
			response.setContentType(getContentType());
			if (logger.isDebugEnabled()) {
				logger.debug("Including [" + getUrl() + "]");
			}
			rd.include(request, response);
		}

		else {
			// Note: The forwarded resource is supposed to determine the content type itself.
			if (logger.isDebugEnabled()) {
				logger.debug("Forwarding to [" + getUrl() + "]");
			}
			rd.forward(request, response);
		}
	}

	/**
	 * Expose helpers unique to each rendering operation. This is necessary so that
	 * different rendering operations can't overwrite each other's contexts etc.
	 * <p>Called by {@link #renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse)}.
	 * The default implementation is empty. This method can be overridden to add
	 * custom helpers as request attributes.
	 *
	 * 对每个渲染方式公开唯一的 helper。不同的渲染方式不能重写彼此的 context 是必要的。
	 * 由 renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) 方法调用
	 * 这个默认实现是空的。可以通过添加自定义的 helper 当做请求属性的方式来重写这个方法。
	 *
	 * @param request current HTTP request
	 * @throws Exception if there's a fatal error while we're adding attributes
	 * @see #renderMergedOutputModel
	 * @see JstlView#exposeHelpers
	 */
	protected void exposeHelpers(HttpServletRequest request) throws Exception {
	}

	/**
	 * Prepare for rendering, and determine the request dispatcher path
	 * to forward to (or to include).
	 * <p>This implementation simply returns the configured URL.
	 * Subclasses can override this to determine a resource to render,
	 * typically interpreting the URL in a different manner.
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @return the request dispatcher path to use
	 * @throws Exception if preparations failed
	 * @see #getUrl()
	 */
	protected String prepareForRendering(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = getUrl();
		Assert.state(path != null, "'url' not set");

		if (this.preventDispatchLoop) {
			String uri = request.getRequestURI();
			if (path.startsWith("/") ? uri.equals(path) : uri.equals(StringUtils.applyRelativePath(uri, path))) {
				throw new ServletException("Circular view path [" + path + "]: would dispatch back " +
						"to the current handler URL [" + uri + "] again. Check your ViewResolver setup! " +
						"(Hint: This may be the result of an unspecified view, due to default view name generation.)");
			}
		}
		return path;
	}

	/**
	 * Obtain the RequestDispatcher to use for the forward/include.
	 * <p>The default implementation simply calls
	 * {@link HttpServletRequest#getRequestDispatcher(String)}.
	 * Can be overridden in subclasses.
	 * @param request current HTTP request
	 * @param path the target URL (as returned from {@link #prepareForRendering})
	 * @return a corresponding RequestDispatcher
	 */
	@Nullable
	protected RequestDispatcher getRequestDispatcher(HttpServletRequest request, String path) {
		return request.getRequestDispatcher(path);
	}

	/**
	 * Determine whether to use RequestDispatcher's {@code include} or
	 * {@code forward} method.
	 *
	 * 确定使用请求分发器的方法， true 是 include()，false 是 forward()。
	 *
	 * forward()：将请求从一个 servlet 转发到服务器上的另一个资源( servlet 、JSP 文件或 HTML 文件)。
	 * 			  该方法允许一个 servlet 对请求进行初步处理，而另一个资源生成响应。
	 *
	 * 			对于通过 getRequestDispatcher() 获取的 RequestDispatcher（请求分发器）,
	 * 			ServletRequest 对象有它的路径元素和参数来调整匹配目标资源的路径。
	 *
	 * 			forward() 应该在响应被提交给客户端（在响应体输出被刷新之前）之前调用。如果响应
	 * 			已经被提交了将会抛出 IllegalStateException 异常.
	 *
	 * 			在 forward() 之前响应体里未提交的输出会被清空。
	 *
	 *
	 * include():在响应中包含资源(servlet、JSP页面、HTML 文件)的内容。本质上，这个方法包括支持编程的服务器端。
	 * 			ServletResponse 对象有与调用者相同的路径元素和参数。包含的 servlet 不能更改响应状态码或设置报头;
	 * 			任何试图改变的尝试都被忽略了。
	 *
	 * 			请求和响应参数必须是传递给调用 servlet 的服务方法的对象，或者是包装它们的 ServletRequestWrapper
	 * 			或 ServletResponseWrapper 类的子类。
	 *
	 * 			这个方法将给定请求的 dispatcher（分发器） 类型设置为 DispatcherType.INCLUDE。
	 *
	 *
	 *
	 * forward方法和include方法的区别：
	 *
	 *                1、forward() 方法调用后在响应中的没有提交的内容被自动消除。将请求转发给其他的 Servlet 后，
	 *                由被调用的 Servlet 负责对请求做出响应，而原先 Servlet 的执行则终止。
	 *
	 *                2、include() 方法使原先的 Servlet 和转发到的 Servlet 都可以输出响应信息，即原先的 Servlet 还可以继续输出响应信息
	 *
	 * <p>Performs a check whether an include URI attribute is found in the request,
	 * indicating an include request, and whether the response has already been committed.
	 * In both cases, an include will be performed, as a forward is not possible anymore.
	 *
	 * 检查在是否在请求找到包含 URI 的属性，这个属性表示是一个 include 请求、是否已经提交响应。
	 * 在这两种情况下，都将执行 include()，因为 forward()不可能。
	 *
	 *
	 *
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @return {@code true} for include, {@code false} for forward
	 * @see javax.servlet.RequestDispatcher#forward
	 * @see javax.servlet.RequestDispatcher#include
	 * @see javax.servlet.ServletResponse#isCommitted
	 * @see org.springframework.web.util.WebUtils#isIncludeRequest
	 */
	protected boolean useInclude(HttpServletRequest request, HttpServletResponse response) {
		return (this.alwaysInclude || WebUtils.isIncludeRequest(request) || response.isCommitted());
	}

}
