package cn.doper.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_login_log")
public class LoginLog implements Serializable {
    private static final long serialVersionUID = -3061069902251151271L;
    private Long id;
    private Long userId;
    private String ip;
    private Date loginTime;
    private Integer type;
}
