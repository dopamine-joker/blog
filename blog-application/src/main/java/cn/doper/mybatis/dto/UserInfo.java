package cn.doper.mybatis.dto;

import lombok.Data;

@Data
public class UserInfo {

    private Long id;
    private String userName;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String userType;
}
