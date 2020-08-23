package com.jqprojects.eshop_inventory.mapper;

import com.jqprojects.eshop_inventory.entity.ProductInventory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInventoryMapperTest {

    @Resource
    public ProductInventoryMapper productInventoryMapper;

    @Test
    @Transactional
    public void updateProductInventory(){

        ProductInventory inventory = new ProductInventory(1, 99L);
        productInventoryMapper.updateProductInventory(inventory);
        ProductInventory inventory1 = productInventoryMapper.findProductInventory(1);
        assertEquals(99L,inventory1.getInventoryCnt());
    }

}