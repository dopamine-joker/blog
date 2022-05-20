package cn.doper.service;

import cn.doper.security.impl.UserDetailsImpl;

public interface UserSecurityService {
    UserDetailsImpl login(String username, String password);

}
