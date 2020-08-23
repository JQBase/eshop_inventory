package com.jqprojects.eshop_inventory.controller;

import com.jqprojects.eshop_inventory.entity.User;
import com.jqprojects.eshop_inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller控制器
 * @author Administrator
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserInfo")
	public User getUserInfo() {
		User user = userService.findUserInfo();
		return user;
	}
	
	@RequestMapping("/getCachedUserInfo")
	public User getCachedUserInfo() {
		User user = userService.getCachedUserInfo();
		return user;
	}
	
}
