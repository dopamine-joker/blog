package cn.doper;

import cn.doper.mybatis.entity.Permission;
import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.mapper.UserMapper;
import cn.doper.redis.service.RedisService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MySpringApplicationTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisService redisService;

    @Resource(name = "stringJsonRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Test
    public void MyBatisTest() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "doper");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void encoder() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    public void omTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 修改序列化反序列化规则
//        objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.ANY);
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        DTO dto = new DTO(1, "123", "321");
        try {
            String s = objectMapper.writeValueAsString(dto);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void valueTest() {
        System.out.println(tokenHeader);
    }

    @Test
    public void testRedis() {
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("key1", new DTO(1, "123", "213"));
//        hashMap.put("key2", new Integer(123));
//        redisService.set("test", hashMap, 64800);
        Set<Permission> set = new HashSet<>();
        set.add(new Permission());
        redisService.set("set", set, 68400);
    }


    @Test
    public void testLocalTime() {
        TimeDTO timeDTO = new TimeDTO("123", LocalDate.now());
        redisService.set("time", timeDTO, 68400);
        TimeDTO time = redisService.get("time", TimeDTO.class);
        System.out.println(time);
    }

}

class TimeDTO {
    private String username;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    private LocalDate localDate;

    public TimeDTO() {
    }

    public TimeDTO(String username, LocalDate localDate) {
        this.username = username;
        this.localDate = localDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TimeDTO{" +
                "username='" + username + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}

class DTO implements Serializable {
    private static final long serialVersionUID = 8968837559722153589L;
    private long id;
    private String name;

    private String password;

    public DTO(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void DTOTest() {
        System.out.println(id + name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
