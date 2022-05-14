package cn.doper.service;

import cn.doper.mybatis.entity.Permission;
import cn.doper.mybatis.entity.User;
import cn.doper.security.dto.LoginUser;

import java.util.List;

public interface UserCacheService {

    @Deprecated
    void delUser(Long id);

    @Deprecated
    void setUser(User user);

    void setLoginUser(LoginUser loginUser);

    boolean delLoginUser(String username);

    LoginUser getLoginUser(String username);

    List<Permission> getUserPermissions(Long id);

    void setUserPermissions(Long id, List<Permission> permission);
}
