package com.danbro.data;

import com.danbro.entities.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserMap
 * @Description TODO
 * @Date 2020/6/23 16:54
 * @Author Danrbo
 */
@Component
public class UserMap{
	private Map<Integer, User> userMap;


	public UserMap() {
		this.userMap = new HashMap<>();
		userMap.put(1,new User(1,"John","12345"));
		userMap.put(2,new User(2,"Alex","12345"));
		userMap.put(3,new User(3,"Wang","12345"));
		userMap.put(4,new User(4,"Josh","12345"));
		userMap.put(5,new User(5,"Sun","12345"));
	}

	public Object get(Object key) {
		return userMap.get(key);
	}

	public void put(Integer key, User value) {
		userMap.put(key,value);
	}
}
