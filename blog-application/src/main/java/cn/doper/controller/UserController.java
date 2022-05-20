package cn.doper.controller;

import cn.doper.common.result.CommonResult;
import cn.doper.domain.UserRegisterDO;
import cn.doper.mybatis.entity.User;
import cn.doper.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody User user) {
        return userLoginService.login(user);
    }

    @PostMapping("/logout")
    public CommonResult<?> logout() {
        return userLoginService.logout();
    }

    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody UserRegisterDO user) {
        return userLoginService.register(user);
    }
}
