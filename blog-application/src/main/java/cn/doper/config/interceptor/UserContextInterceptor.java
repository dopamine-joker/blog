package cn.doper.config.interceptor;

import cn.doper.common.context.UserContextHolder;
import cn.doper.common.result.impl.ResultCode;
import cn.doper.exception.BusinessException;
import cn.doper.security.dto.LoginUser;
import cn.doper.security.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return Optional
//                .ofNullable(authentication)
//                .map(a -> {
//                    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//                    LoginUser loginUser = userDetails.getLoginUser();
//                    UserContextHolder.set(loginUser.getId(), loginUser.getUserName());
//                    return true;
//                })
//                .orElseThrow(() -> new BusinessException(ResultCode.UNAUTHORIZED));
        if(authentication == null) {
            throw new BusinessException(ResultCode.UNAUTHORIZED);
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LoginUser loginUser = userDetails.getLoginUser();
        UserContextHolder.set(loginUser.getId(), loginUser.getUserName());
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.remove();
    }
}
