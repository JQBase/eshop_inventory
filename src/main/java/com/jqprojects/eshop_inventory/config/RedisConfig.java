package com.jqprojects.eshop_inventory.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * REDIS的配置类
 *
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.cache.nodes:}")
    private String nodes;
    @Value("${spring.redis.cache.host:}")
    private String host;
    @Value("${spring.redis.cache.password:}")
    private String password;
    @Value("${spring.redis.cache.maxIdle:8}")
    private Integer maxIdle;
    @Value("${spring.redis.cache.minIdle:1}")
    private Integer minIdle;
    @Value("${spring.redis.cache.maxTotal:8}")
    private Integer maxTotal;
    @Value("${spring.redis.cache.maxWaitMillis:5000}")
    private Long maxWaitMillis;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        // 连接池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .build();

        // 单机redis
//        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
//        redisConfig.setHostName(host==null||"".equals(host)?"localhost":host.split(":")[0]);
//        redisConfig.setPort(Integer.valueOf(host==null||"".equals(host)?"6379":host.split(":")[1]));
//        if (password != null && !"".equals(password)) {
//            redisConfig.setPassword(password);
//        }

        // 哨兵redis
        // RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();

        // 集群redis
        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
        Set<RedisNode> nodeses = new HashSet<>();
        String[] hostses = nodes.split(",");
        for (String h : hostses) {
            h = h.replaceAll("\\s", "").replaceAll("\n", "");
            if (!"".equals(h)) {
                String host = h.split(":")[0];
                int port = Integer.valueOf(h.split(":")[1]);
                nodeses.add(new RedisNode(host, port));
            }
        }
        redisConfig.setClusterNodes(nodeses);
        // 跨集群执行命令时要遵循的最大重定向数量
        redisConfig.setMaxRedirects(3);
//        redisConfig.setPassword(password);

        return new LettuceConnectionFactory(redisConfig, lettucePoolingClientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        //key序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        //value序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.setHashKeySerializer(stringRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
