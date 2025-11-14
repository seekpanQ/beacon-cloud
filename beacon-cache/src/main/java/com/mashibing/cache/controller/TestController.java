package com.mashibing.cache.controller;

import com.msb.framework.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * redis测试
 */
@RestController
public class TestController {

    @Autowired
    private RedisClient redisClient;


    /**
     * 写测试   hash结构
     *
     * @param key
     * @param map
     * @return
     */
    @PostMapping("/test/set/{key}")
    public String set(@PathVariable String key, @RequestBody Map map) {
        redisClient.set(key, map);
        return "ok";
    }

    /**
     * 读测试
     *
     * @param key
     * @return
     */

    @GetMapping("/test/get/{key}")
    public Map get(@PathVariable String key) {
        Map<Object, Object> result = redisClient.get(key);
        return result;
    }
}
