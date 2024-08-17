package com.user.model.dto.user;

import lombok.Data;

@Data
public class UserRegisterRequest {

    String userAccount;
    String userPassword;
    String checkPassword;
    String phone;
    short gender;
}
