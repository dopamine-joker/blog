package cn.doper.Service;

import cn.doper.common.CommonResult;
import cn.doper.mybatis.entity.User;

public interface UserLoginService {

    CommonResult<?> login(User user);
}
