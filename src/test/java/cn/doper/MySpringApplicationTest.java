package cn.doper;

import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MySpringApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Test
    public void MyBatisTest() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "doper");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    //$2a$10$MsW95fjD8zbsFI1VOAqPaOapRW4wc91fCw6ipTvL8FR2T4Jv1v89u
    //$2a$10$2q/mb/jqqleupBnjr1FRwe9PLBCEjec/IhShkS3wT2TYrUg4zDySC
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
}
