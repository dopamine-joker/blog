package cn.doper.service;

import cn.doper.common.result.CommonResult;
import cn.doper.domain.UserRegisterDO;
import cn.doper.mybatis.entity.User;

public interface UserLoginService {

    CommonResult<?> login(User user);

    CommonResult<?> logout();

    CommonResult<?> register(UserRegisterDO user);
}
