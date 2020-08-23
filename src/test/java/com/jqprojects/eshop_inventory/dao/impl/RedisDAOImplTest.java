package com.jqprojects.eshop_inventory.dao.impl;

import com.jqprojects.eshop_inventory.dao.RedisDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisData;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
class RedisDAOImplTest {

    @Autowired
    RedisDAO redisDAO;

    @Test
    void set() {
        redisDAO.set("product:inventory:1","100");
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }
}