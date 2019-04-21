package me.leig.task.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Override
    public void setCache(String key, String value) {
        setCache(key, value, 60);
    }

    @Override
    public void setCache(String key, String value, long time) {
        setCache(key, value, time, TimeUnit.SECONDS);
    }

    @CachePut(value = "value", key = "key")
    @Override
    public void setCache(String key, String value, long time, TimeUnit timeUnit) {
        log.info("为Key: {}, Value: {}, 实现缓存", key, value);
    }

    @Override
    public String getCache(String key) {
        return null;
    }
}
