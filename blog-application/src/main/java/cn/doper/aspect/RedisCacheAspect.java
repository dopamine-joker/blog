package cn.doper.aspect;


import cn.doper.annotation.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class RedisCacheAspect {

    @Pointcut("execution(public * cn.doper.service.*CacheService.*(..))")
    public void cacheAspect() {

    }

    /**
     *  JoinPoint 与 ProceedingJoinPoint 的区别
     *  JoinPoint 可以获取切入点的对象，参数等信息
     *  ProceedingJoinPoint继承JoinPoint,在其基础上暴露了proceed方法，这个是aop代理链执行的方法。
     *  环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的
     */
    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature(); //切入点信息
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;
        try {
            result = joinPoint.proceed();   //方法执行
        } catch (Throwable e) {
            if(method.isAnnotationPresent(CacheException.class)) {
                throw e;
            } else {
                log.error("缓存出错{}, 方法继续执行", e.getMessage());
            }
        }
        return result;
    }
}
