package cn.doper.service;

import cn.doper.mybatis.entity.LoginLog;

public interface UserKafkaService {
    void insertLoginLog(LoginLog loginLog);
}
