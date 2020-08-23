package com.jqprojects.eshop_inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 库存数量model
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
public class ProductInventory {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 库存数量
	 */
	private Long inventoryCnt;

}
