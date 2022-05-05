package cn.doper.security.utils;

import cn.doper.mybatis.entity.User;
import cn.doper.security.dto.LoginUser;
import cn.hutool.core.bean.BeanUtil;

public class UserConvertor {
    public static LoginUser toLoginUser(User user) {
        return BeanUtil.copyProperties(user, LoginUser.class);
    }
}
