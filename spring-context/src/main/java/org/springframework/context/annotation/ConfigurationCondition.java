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

package org.springframework.context.annotation;

/**
 * A {@link Condition} that offers more fine-grained control when used with
 * {@code @Configuration}. Allows certain {@link Condition Conditions} to adapt when they match
 * based on the configuration phase. For example, a condition that checks if a bean
 * has already been registered might choose to only be evaluated during the
 * {@link ConfigurationPhase#REGISTER_BEAN REGISTER_BEAN} {@link ConfigurationPhase}.
 *
 * 当使用 @Configuration 能提供更细颗粒度控制的条件。允许某些条件根据匹配配置阶段时进行调整。
 * 例如：检查一个 bean 是否已注册的条件可能会选择在 REGISTER_BEAN 配置阶段期间。
 *
 *
 * @author Phillip Webb
 * @since 4.0
 * @see Configuration
 */
public interface ConfigurationCondition extends Condition {

	/**
	 * Return the {@link ConfigurationPhase} in which the condition should be evaluated.
	 */
	ConfigurationPhase getConfigurationPhase();


	/**
	 * The various configuration phases where the condition could be evaluated.
	 *
	 * 表示现在的配置阶段
	 *
	 */
	enum ConfigurationPhase {

		/**
		 * The {@link Condition} should be evaluated as a {@code @Configuration}
		 * class is being parsed.
		 * <p>If the condition does not match at this point, the {@code @Configuration}
		 * class will not be added.
		 *
		 * 解析阶段是指当在解析 @Configuration 配置类时的阶段。
		 * 如果此时条件不匹配，则不会添加被 @Configuration 注解的配置类。
		 */
		PARSE_CONFIGURATION,

		/**
		 * The {@link Condition} should be evaluated when adding a regular
		 * (non {@code @Configuration}) bean. The condition will not prevent
		 * {@code @Configuration} classes from being added.
		 * <p>At the time that the condition is evaluated, all {@code @Configuration}s
		 * will have been parsed.
		 *
		 * 当添加一个普通的 bean（没有 @Configuration 注解）时的评估条件。这个条件将不会阻止添加 @Configuration 配置类。
		 * 当阶段为注册 Bean 时所有的 @Configuration 都会被解析。
		 *
		 */
		REGISTER_BEAN
	}

}
