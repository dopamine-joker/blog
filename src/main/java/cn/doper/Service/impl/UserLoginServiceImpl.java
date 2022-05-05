package cn.doper.Service.impl;

import cn.doper.Service.UserLoginService;
import cn.doper.Service.UserSecurityService;
import cn.doper.common.CommonResult;
import cn.doper.constants.UserToken;
import cn.doper.mybatis.entity.User;
import cn.doper.redis.service.RedisService;
import cn.doper.security.dto.LoginUser;
import cn.doper.security.impl.UserDetailsImpl;
import cn.doper.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * user顶层service
 * @author doper
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public CommonResult<?> login(User user) {
        UserDetailsImpl userDetails = userSecurityService.login(user.getUserName(), user.getPassword());
        // TODO: 处理认证后的用户信息
        // 1. 生成token
        String token = jwtUtils.generateToken(userDetails.getLoginUser());
        // 2. 消息存redis
        LoginUser loginUser = userDetails.getLoginUser();
        String tokenKey = UserToken.TOKEN_REDIS_PREFIX + loginUser.getId();
        redisService.set(tokenKey, loginUser);
        return CommonResult.success(token);
    }
}
