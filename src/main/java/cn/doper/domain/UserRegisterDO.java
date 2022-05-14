package cn.doper.domain;

import lombok.Data;

@Data
public class UserRegisterDO {

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;
}
