package cn.doper.controller;

import cn.doper.Service.UserLoginService;
import cn.doper.common.CommonResult;
import cn.doper.mybatis.entity.User;
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
}
