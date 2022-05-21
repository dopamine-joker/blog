package cn.doper.mybatis.service;

import java.util.Date;

public interface LoginLogService {
    boolean addLog(Long userId, String ip, Date date, Integer type);
}
