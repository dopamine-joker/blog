package cn.doper.Service;

import cn.doper.security.impl.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserSecurityService {
    UserDetailsImpl login(String username, String password);

}
