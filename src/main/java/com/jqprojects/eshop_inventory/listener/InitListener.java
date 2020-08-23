package com.jqprojects.eshop_inventory.listener;

import com.jqprojects.eshop_inventory.thread.RequestProcessorThreadPool;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 系统初始化监听器
 * @author Administrator
 *
 */
@Slf4j
@WebListener
public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 初始化工作线程池和内存队列
		log.debug("初始化内部任务队列开始....");
		RequestProcessorThreadPool.init();
		log.debug("初始化内部任务队列结束....");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
