package cn.doper.mybatis.service.impl;

import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.mapper.UserMapper;
import cn.doper.mybatis.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        return this.getOne(queryWrapper);
    }

    @Override
    public User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNumber, phone);
        return this.getOne(queryWrapper);
    }

    @Override
    public User getUserById(Long userId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, userId);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean insertUser(String username, String password, String phoneNumber, String email) {
        User insertUser = new User();
        insertUser.setUserName(username);
        insertUser.setPassword(password);
        insertUser.setPhoneNumber(phoneNumber);
        insertUser.setEmail(email);
        insertUser.setStatus(0);
        insertUser.setCreateTime(new Date());
        insertUser.setUpdateTime(new Date());
        insertUser.setDelFlag(0);
        return this.save(insertUser);
    }


}
