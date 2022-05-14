package cn.doper.service.impl;

import cn.doper.common.result.impl.ResultCode;
import cn.doper.exception.BusinessException;
import cn.doper.service.UserCacheService;
import cn.doper.service.UserLoginService;
import cn.doper.service.UserSecurityService;
import cn.doper.common.result.CommonResult;
import cn.doper.domain.UserRegisterDO;
import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.service.UserService;
import cn.doper.security.impl.UserDetailsImpl;
import cn.doper.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * user顶层service
 *
 * @author doper
 */
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CommonResult<?> login(User user) {
        UserDetailsImpl userDetails = userSecurityService.login(user.getUserName(), user.getPassword());
        // TODO: 处理认证后的用户信息
        // 1. 生成token
        String token = jwtUtils.generateToken(userDetails.getLoginUser());
        // 2. 返回
        return CommonResult.response(ResultCode.USER_OK, token);
    }

    @Override
    public CommonResult<?> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean res = userCacheService.delLoginUser(userDetails.getUsername());
        if (!res) {
            log.error("退出登陆失败{}", userDetails.getUsername());
            throw new BusinessException(ResultCode.USER_FAILED);
        }
        return CommonResult.response(ResultCode.USER_OK, null);
    }

    @Override
    public CommonResult<?> register(UserRegisterDO user) {
        String username = user.getUserName();
        String password = passwordEncoder.encode(user.getPassword());
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();
        if(userService.getUserByPhone(phoneNumber) != null) {
            throw new BusinessException(ResultCode.USER_PHONE_DUPLICATE);
        }
        if(userService.getUserByUserName(user.getUserName()) != null) {
            throw new BusinessException(ResultCode.USER_NAME_DUPLICATE);
        }
        if (!userService.registerUser(username, password, phoneNumber, email)) {
            throw new BusinessException(ResultCode.USER_FAILED);
        }
        return CommonResult.response(ResultCode.USER_OK, null);
    }

}
