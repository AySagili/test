package com.user.model.entity;

import java.sql.Time;

import lombok.Data;

/**
 * User
 */
@Data
public class User {
    long id;
    String username;
    String userAccount;
    short gender;
    String password;
    String phone;
    Time createTime;
    Time updateTime;
    int isDelete;
}