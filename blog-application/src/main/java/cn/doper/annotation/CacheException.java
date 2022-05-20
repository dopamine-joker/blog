package cn.doper.annotation;

import java.lang.annotation.*;

/**
 * 给某些缓存无关精要的方法添加，用来忽略这些方法的缓存异常
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
