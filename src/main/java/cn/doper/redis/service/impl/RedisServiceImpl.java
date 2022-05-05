package cn.doper.redis.service.impl;

import cn.doper.redis.service.RedisService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource(name = "stringJsonRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> targetClass) {
        Object val = redisTemplate.opsForValue().get(key);
        if (targetClass == String.class) {
            return (T) StrUtil.toStringOrNull(val);
        }
        return BeanUtil.toBean(val, targetClass);
    }

    @Override
    public <T> void set(String key, T val) {
        redisTemplate.opsForValue().set(key, val);
    }
}
