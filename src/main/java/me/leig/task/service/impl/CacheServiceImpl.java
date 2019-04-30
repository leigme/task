package me.leig.task.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.Constant;
import me.leig.task.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void setCache(String key, String value) {
        setCache(key, value, 60);
    }

    @Override
    public void setCache(String key, String value, long time) {
        setCache(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void setCache(String key, String value, long time, TimeUnit timeUnit) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return;
        }
        try {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
        } catch (Exception e) {
            log.error("向Redis缓存数据异常: {}", e.getMessage(), e);
        }
    }

    @Override
    public String getCache(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("从Redis获取缓存异常: {}", e.getMessage(), e);
        }
        return null;
    }
}
