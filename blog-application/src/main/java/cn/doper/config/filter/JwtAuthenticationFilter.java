package cn.doper.config.filter;

import cn.doper.security.dto.LoginUser;
import cn.doper.service.UserCacheService;
import cn.doper.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * jwt过滤器，在securityConfig中注册bean
 *
 * @author doper
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserCacheService userCacheService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 从header获取token
        String token = request.getHeader(tokenHeader);
        // 2. token为空jwt解析过期
        if (!StringUtils.hasText(token) || jwtUtils.isTokenExpired(token)) {
            // 放行，后续security根据配置和context检测
            filterChain.doFilter(request, response);
            return;
        }
        // 2. 判断用户是否登陆
        String username = jwtUtils.getUserNameFromToken(token);
        LoginUser loginUser = userCacheService.getLoginUser(username);
        if (loginUser == null) {
            // 放行，后续security根据配置和context检测
            filterChain.doFilter(request, response);
            return;
        }
        // 3. 解析token信息，获取信息后注入security
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
