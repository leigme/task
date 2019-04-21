package me.leig.task.service;

import java.util.concurrent.TimeUnit;

public interface CacheService {

    void setCache(String key, String value);

    void setCache(String key, String value, long time);

    void setCache(String key, String value, long time, TimeUnit timeUnit);

    String getCache(String key);
}
