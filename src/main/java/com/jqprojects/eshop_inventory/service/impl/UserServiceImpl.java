package com.jqprojects.eshop_inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jqprojects.eshop_inventory.dao.RedisDAO;
import com.jqprojects.eshop_inventory.entity.User;
import com.jqprojects.eshop_inventory.mapper.UserMapper;

import com.jqprojects.eshop_inventory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户Service实现类
 * @author Administrator
 *
 */
@Service("userService")  
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private RedisDAO redisDAO;
	
	@Override
	public User findUserInfo() {
		return userMapper.findUserInfo();
	}

	@Override
	public User getCachedUserInfo() {
		redisDAO.set("cached_user_lisi", "{\"name\": \"lisi\", \"age\":28}");
		
		String userJSON = (String) redisDAO.get("cached_user_lisi");
		JSONObject userJSONObject = JSONObject.parseObject(userJSON);
		
		User user = new User();
		user.setName(userJSONObject.getString("name"));   
		user.setAge(userJSONObject.getInteger("age"));  
		
		return user;
	}

}
