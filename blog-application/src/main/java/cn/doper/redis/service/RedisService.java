package cn.doper.redis.service;

import java.util.List;

public interface RedisService {

    <T> T get(String key, Class<T> targetClass);

    <E> List<E> getList(String key, Class<E> elementClass);

    <T> void set(String key, T val, long time);

    Boolean del(String key);
}
