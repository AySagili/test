package com.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.user.model.dto.user.UserLoginRequest;
import com.user.model.dto.user.UserQueryRequest;
import com.user.model.dto.user.UserRegisterRequest;
import com.user.model.dto.user.UserUpdateRequest;
import com.user.model.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author zhexueqi
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-08-15 16:32:30
 */
public interface UserService extends IService<UserRegisterRequest> {

    /**
     * 注册用户
     * 
     * @param userRegisterRequest
     * @return
     */
    long registerUser(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     * 
     * @param userLoginRequest
     * @return
     */
    String userLogin(UserLoginRequest userLoginRequest);

    /**
     * 获取登录用户
     * 
     * @param request
     * @return
     */
    UserVO getLoginUser(HttpServletRequest request);

    /**
     * 根据用户名模糊匹配用户列表
     * 
     * @param userQueryRequest
     * @return
     */
    IPage<UserVO> queryUserListByUserNameLike(UserQueryRequest userQueryRequest);

    /**
     * 修改用户
     * 
     * @param userUpdateRequest
     * @return
     */
    boolean updateUserById(UserUpdateRequest userUpdateRequest);

    boolean deleteUserByAcount(String acount);
}
