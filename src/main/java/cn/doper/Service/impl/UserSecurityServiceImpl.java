package cn.doper.Service.impl;

import cn.doper.Service.UserSecurityService;
import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.service.UserService;
import cn.doper.security.impl.UserDetailsImpl;
import cn.doper.security.utils.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetailsImpl login(String username, String password) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate) || !authenticate.isAuthenticated()) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        // 得到认证后的用户
        // TODO: 其他操作
        return userDetails;
    }

}
