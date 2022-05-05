package cn.doper.filter;

import cn.doper.redis.service.RedisService;
import cn.doper.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * jwt过滤器，在securityConfig中注册bean
 * @author doper
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisService redisService;

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
        if(!StringUtils.hasText(token)) {
            // 放行，后续security根据配置和context检测
            filterChain.doFilter(request, response);
            return ;
        }
        // 2. jwt解析过期
        if (jwtUtils.isTokenExpired(token)) {
            throw new RuntimeException("用户登陆过期");
        }
        // 3. 解析token信息，获取信息后注入security
        String username = jwtUtils.getUserNameFromToken(token);
//        long userId = jwtUtils.getUserIdFromToken(token);
//        String redisKey = UserToken.TOKEN_REDIS_PREFIX + userId;
//        LoginUser loginUser = redisService.get(redisKey, LoginUser.class);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
