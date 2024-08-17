package com.user.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.model.dto.user.UserLoginRequest;
import com.user.model.dto.user.UserRegisterRequest;
import com.user.model.dto.user.UserUpdateRequest;
import com.user.model.entity.User;

/**
 * @author zhexueqi
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-08-15 16:32:30
 * @Entity com.user.model.entity.User
 */
public interface UserMapper extends BaseMapper<UserRegisterRequest> {

    long FindIdByAccount(String userAccount);

    void RegisterUser(String userAccount, String userPassword, String phone, short gender);

    User LoginUser(String userAccount, String userPassword);

    Boolean UpdateUserById(int id, String username, String phone, String userAccount, short gender, String password);

    @Delete("delete from user where user_account = #{userAccount}")
    int DeleteByAcount(String userAccount);
}
