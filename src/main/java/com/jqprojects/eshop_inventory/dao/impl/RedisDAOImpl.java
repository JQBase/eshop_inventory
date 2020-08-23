package com.jqprojects.eshop_inventory.dao.impl;

import javax.annotation.Resource;

import com.jqprojects.eshop_inventory.dao.RedisDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;



@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key,value);
	}

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

}
