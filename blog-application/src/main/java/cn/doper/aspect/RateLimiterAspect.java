package cn.doper.aspect;


import cn.doper.annotation.RateLimiter;
import cn.doper.common.result.impl.ResultCode;
import cn.doper.constant.LimitType;
import cn.doper.exception.BusinessException;
import cn.doper.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class RateLimiterAspect {
    @Resource(name = "stringJsonRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisScript<Long> limitScript;

    @Before("@annotation(rateLimiter)")
    public void before(JoinPoint point, RateLimiter rateLimiter) {
        String key = rateLimiter.key();
        int time = rateLimiter.time();
        int count = rateLimiter.count();

        String combineKey = getCombineKey(point, rateLimiter);
        List<String> keys = Collections.singletonList(combineKey);
        Long number = redisTemplate.execute(limitScript, keys, count, time);
        if (Objects.isNull(number) || number.intValue() > count) {
            throw new BusinessException(ResultCode.ACCESS_LIMIT);
        }
        log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), keys.get(0));
    }

    public String getCombineKey(JoinPoint point, RateLimiter rateLimiter) {
        StringBuilder stringBuilder = new StringBuilder(rateLimiter.key());
        if (rateLimiter.limitType().equals(LimitType.IP)) {
            stringBuilder
                    .append(RequestUtil.getRequestIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()))
                    .append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuilder.append(targetClass.getName());
        return stringBuilder.toString();
    }

}
