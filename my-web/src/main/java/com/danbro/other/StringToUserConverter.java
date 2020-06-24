package com.danbro.other;

import com.danbro.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Classname MyConverter 字符串转换成 User 对象的转换器
 * @Description TODO
 * @Date 2020/6/23 20:56
 * @Author Danrbo
 */
@Component
public class StringToUserConverter implements Converter<String, User> {
	@Override
	public User convert(String source) {
		// 字符串是 7-danbro-123456
		String[] split = source.split("-");
		if (StringUtils.hasText(source) && split.length == 3){
			String id = split[0];
			String username = split[1];
			String password = split[2];
			return new User(Integer.parseInt(id), username, password);
		}
		return null;
	}
}
