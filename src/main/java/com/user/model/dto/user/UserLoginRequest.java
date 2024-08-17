package com.user.model.dto.user;

import lombok.Data;

@Data
public class UserLoginRequest {
    String userAccount;
    String userPassword;
}
