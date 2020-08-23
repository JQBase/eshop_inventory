package com.jqprojects.eshop_inventory.service;


import com.jqprojects.eshop_inventory.request.Request;

/**
 * 请求异步执行的service
 * @author Administrator
 *
 */
public interface RequestAsyncProcessService {

	void process(Request request);
	
}
