package cn.doper.mybatis.service;

import cn.doper.mybatis.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User getUserByUserName(String username);

    User getUserByPhone(String phone);
}
