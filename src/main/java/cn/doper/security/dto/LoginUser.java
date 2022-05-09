package cn.doper.security.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private Long id;
    private String userName;
    private String password;
    private Integer delFlag;
    private Integer status;
}
