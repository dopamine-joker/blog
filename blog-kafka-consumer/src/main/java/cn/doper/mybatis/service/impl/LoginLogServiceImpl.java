package cn.doper.mybatis.service.impl;

import cn.doper.mybatis.entity.LoginLog;
import cn.doper.mybatis.mapper.LoginLogMapper;
import cn.doper.mybatis.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public boolean addLog(Long userId, String ip, Date date, Integer type) {
        LoginLog log = new LoginLog();
        log.setUserId(userId);
        log.setIp(ip);
        log.setLoginTime(date);
        log.setType(type);
        return loginLogMapper.insert(log) > 0;
    }
}
