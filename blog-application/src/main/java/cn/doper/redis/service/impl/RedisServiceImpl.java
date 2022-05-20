package cn.doper.redis.service.impl;

import cn.doper.redis.service.RedisService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Resource(name = "stringJsonRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取非Collection类型的redis数据
     * @param key
     * @param targetClass
     * @return
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> targetClass) {
        Object val = redisTemplate.opsForValue().get(key);
        if (targetClass == String.class) {
            return (T) StrUtil.toStringOrNull(val);
        } else if(!Objects.isNull(val) && targetClass == val.getClass()) {
            return (T) val;
        }
        return BeanUtil.toBean(val, targetClass);
    }

    /**
     * 得到存入redis中的{@link List}(这里不是redis中的list)以list的形式取出来
     * @param key
     * @return
     * @param <E>
     */
    @Override
    public <E> List<E> getList(String key, Class<E> elementClass) {
        Object o = redisTemplate.opsForValue().get(key);    //因为redis config,这里获取的json会自动转化为arraylist
        return BeanUtil.copyToList((Collection<?>) o, elementClass);
    }

    @Override
    public <T> void set(String key, T val, long time) {
        redisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
}
