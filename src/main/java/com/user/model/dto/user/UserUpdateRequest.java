package com.user.model.dto.user;

import lombok.Data;

@Data
public class UserUpdateRequest {
    int id;
    String username;
    String phone;
    String userAccount;
    short gender;
    String password;
}
