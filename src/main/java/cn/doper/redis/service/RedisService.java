package cn.doper.redis.service;

public interface RedisService {

    <T> T get(String key, Class<T> targetClass);

    <T> void set(String key, T val);

}
