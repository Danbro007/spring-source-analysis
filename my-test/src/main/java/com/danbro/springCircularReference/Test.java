package com.danbro.springCircularReference;

import com.danbro.springCircularReference.Bean.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * <p>
 * 构造器注入的循环引用问题：
 * A 和 B 互相循环引用，它们通过构造器互相注入，但是循环引用无法解决。
 * 会抛出 BeanCurrentlyInCreationException 异常
 * 我们分以下四种情况测试：
 * 1、A 和 B 都是用构造器注入，无法解决循环引用问题。
 * 2、A 和 B 都是用 Setter 注入，可以解决循环引用问题。
 * 3、A 采用构造器注入，B 采用 Setter 注入，可以解决循环引用问题。
 * 4、A 采用 Setter 注入，B 采用构造器注入，可以解决循环引用问题。
 * @Date 2020/6/9 19:41
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");
//		A a = (A) applicationContext.getBean(A.class);
//		B b = (B) applicationContext.getBean(B.class);
//		C c = (C) applicationContext.getBean(C.class);
//		D d = (D) applicationContext.getBean(D.class);
		E e = (E) applicationContext.getBean(E.class);
		F f = (F) applicationContext.getBean(F.class);
//		G g = (G) applicationContext.getBean( G.class);
//		H h = (H) applicationContext.getBean( H.class);

//		UserService userService = (UserService)applicationContext.getBean("userService");
	}
}
