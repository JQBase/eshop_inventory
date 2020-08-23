package com.jqprojects.eshop_inventory.request;

/**
 * 请求接口
 * @author Administrator
 *
 */
public interface Request {
	
	void process();
	Integer getProductId();
	boolean isForceRefresh();
	
}
