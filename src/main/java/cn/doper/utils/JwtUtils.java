package cn.doper.utils;

import cn.doper.security.dto.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * Created by macro on 2018/4/26.
 * <p>
 * payload JWT中的7个默认字段
 * - exp：到期时间
 * - iss：发行人
 * - sub：主题
 * - aud：用户
 * - nbf：在此之前不可用
 * - iat：签发时间
 * - jti：JWT ID用于标识该JWT
 */
@Component
@ConditionalOnProperty(prefix = "jwt", name = "secret")
@Slf4j
public class JwtUtils {

    private final static String CLAIM_KEY_CREATED = "created";

    private final static String CLAIM_KEY_UID = "uid";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationTime())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 对外暴露的生成token接口
     *
     * @param user
     * @return
     */
    public String generateToken(LoginUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, user.getUserName());       //sub
        claims.put(Claims.ISSUED_AT, new Date());   // iat
        claims.put(CLAIM_KEY_UID, user.getId());
        return generateToken(claims);
    }

    /**
     * 从token解析claims
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT从token获取claims失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token过期时间
     *
     * @return
     */
    private Date generateExpirationTime() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredTime = getTokenExpiredTime(token);
        return expiredTime.before(new Date());
    }

    /**
     * 得到过期时间
     *
     * @param token
     * @return
     */
    private Date getTokenExpiredTime(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token获取用户名
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            log.error("token获取用户名失败, token:{}", token);
            username = null;
        }
        return username;
    }

    /**
     * 从token中提取uid
     * @param token
     * @return
     */
    public long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Long uid = claims.get(CLAIM_KEY_UID, Long.class);
        if (Objects.isNull(uid)) {
            log.error("token获取id失败，token:{}", token);
        }
        return uid;
    }

}
