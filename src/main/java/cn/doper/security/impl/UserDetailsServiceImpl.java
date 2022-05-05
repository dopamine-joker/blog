package cn.doper.security.impl;

import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.service.UserService;
import cn.doper.security.utils.UserConvertor;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // TODO: 权限查询
        List<String> permissionList = Lists.newArrayList("test");
        return new UserDetailsImpl(UserConvertor.toLoginUser(user), permissionList);
    }
}
