package cn.doper.security.impl;

import cn.doper.Service.UserCacheService;
import cn.doper.mybatis.entity.Permission;
import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.service.PermissionService;
import cn.doper.mybatis.service.UserService;
import cn.doper.security.dto.LoginUser;
import cn.doper.security.utils.UserConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private PermissionService permissionService;

    private LoginUser getLoginUser(String username) {
        LoginUser loginUser = userCacheService.getLoginUser(username);
        if(!Objects.isNull(loginUser)) {
            return loginUser;
        }
        // 这里若还是查不到用户，则loginUser还是为空
        User user = userService.getUserByUserName(username);
        if(!Objects.isNull(user)) {
            loginUser = UserConvertor.toLoginUser(user);
            userCacheService.setLoginUser(loginUser);
        }
        return loginUser;
    }

    private List<Permission> getPermissions(Long userId) {
        List<Permission> permissions = userCacheService.getUserPermissions(userId);
        if (!Objects.isNull(permissions)) {
            return permissions;
        }
        permissions = permissionService.getPermissions(userId);
        if (!Objects.isNull(permissions)) {
            userCacheService.setUserPermissions(userId, permissions);
        }
        return permissions;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.用户信息
        LoginUser loginUser = getLoginUser(username);
        if (Objects.isNull(loginUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // TODO: 权限查询
        // 2.权限
        List<Permission> permissions = getPermissions(loginUser.getId());
        return new UserDetailsImpl(loginUser, permissions);
    }
}
