package com.jqprojects.eshop_inventory.request;

import com.jqprojects.eshop_inventory.entity.ProductInventory;
import com.jqprojects.eshop_inventory.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * 重新加载商品库存的缓存
 * @author Administrator
 *
 */
@Slf4j
public class ProductInventoryCacheRefreshRequest implements Request {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 商品库存Service
	 */
	private ProductInventoryService productInventoryService;
	/**
	 * 是否强制刷新缓存
	 */
	private boolean forceRefresh;
	
	public ProductInventoryCacheRefreshRequest(Integer productId,
			ProductInventoryService productInventoryService,
			boolean forceRefresh) {
		this.productId = productId;
		this.productInventoryService = productInventoryService;
		this.forceRefresh = forceRefresh;
	}
	
	@Override
	public void process() {
		// 从数据库中查询最新的商品库存数量
		ProductInventory productInventory = productInventoryService.findProductInventory(productId);
		log.info(" 已查询到商品最新的库存数量，商品id=[{}], 商品库存数量=[{}]" ,productId,productInventory.getInventoryCnt());
		// 将最新的商品库存数量，刷新到redis缓存中去
		productInventoryService.setProductInventoryCache(productInventory); 
	}
	
	public Integer getProductId() {
		return productId;
	}

	public boolean isForceRefresh() {
		return forceRefresh;
	}
	
}
