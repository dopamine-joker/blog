package cn.doper.service.impl;

import cn.doper.service.UserCacheService;
import cn.doper.mybatis.entity.Permission;
import cn.doper.mybatis.entity.User;
import cn.doper.redis.service.RedisService;
import cn.doper.security.dto.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户相关缓存service
 */
@Service
@Slf4j
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.user-cache-prefix}")
    private String userCachePrefix;

    @Value("${redis.key.user-cache-info}")
    private String userInfoKey;

    @Value("${redis.key.permission-cache-prefix}")
    private String permissionCachePrefix;

    @Value("${redis.expire.common}")
    private long expiredTime;

    @Override
    public void delUser(Long id) {

    }

    @Override
    public void setUser(User user) {

    }

    @Override
    public void setLoginUser(LoginUser loginUser) {
        String key = userCachePrefix + ":" + userInfoKey + ":" + loginUser.getUserName();
        redisService.set(key, loginUser, expiredTime);
    }


    @Override
    public boolean delLoginUser(String username) {
        String key = userCachePrefix + ":" + userInfoKey + ":" + username;
        return redisService.del(key);
    }

    @Override
    public LoginUser getLoginUser(String username) {
        String key = userCachePrefix + ":" + userInfoKey + ":" + username;
        return redisService.get(key, LoginUser.class);
    }

    @Override
    public List<Permission> getUserPermissions(Long userId) {
        String key = permissionCachePrefix + ":" + userId;
        return redisService.get(key, ArrayList.class);
//        return redisService.getList(key, Permission.class);
    }

    @Override
    public void setUserPermissions(Long userId, List<Permission> permission) {
        String key = permissionCachePrefix + ":" + userId;
        redisService.set(key, permission, expiredTime);
    }
}
