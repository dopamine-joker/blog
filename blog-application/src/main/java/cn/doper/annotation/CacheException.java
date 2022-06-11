package cn.doper.annotation;

import java.lang.annotation.*;

/**
 * Service中缓存默认不影响
 * 给缓存会影响的方法添加，当缓存出错时会抛出异常
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
