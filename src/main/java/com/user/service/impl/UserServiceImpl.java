package com.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.Exception.BusinessException;
import com.user.common.ErrorCode;
import com.user.mapper.UserMapper;
import com.user.model.dto.user.UserLoginRequest;
import com.user.model.dto.user.UserQueryRequest;
import com.user.model.dto.user.UserRegisterRequest;
import com.user.model.dto.user.UserUpdateRequest;
import com.user.model.entity.User;
import com.user.model.vo.UserVO;
import com.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhexueqi
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-08-15 16:32:30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserRegisterRequest>
        implements UserService {

    private final UserMapper userMapper;
    private static final String SALT = "user-center";

    @Override
    public long registerUser(UserRegisterRequest userRegisterRequest) {
        if (!userRegisterRequest.getUserPassword().equals(userRegisterRequest.getCheckPassword())) {

        }
        userMapper.RegisterUser(userRegisterRequest.getUserAccount(),
                userRegisterRequest.getCheckPassword(),
                userRegisterRequest.getPhone(), userRegisterRequest.getGender());

        return userMapper.FindIdByAccount(userRegisterRequest.getUserAccount());
    }

    @Override
    /**
     * 用户登录方法
     *
     * @param userLoginRequest 用户登录请求对象，包含用户账号和密码
     * @return 返回一个UserVO对象，其中包含用户信息和登录令牌
     */
    public String userLogin(UserLoginRequest userLoginRequest) {
        User user = userMapper.LoginUser(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword());
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("username", user.getUsername());
        payload.put("userAccount", user.getUserAccount());
        payload.put("gender", user.getGender());
        payload.put("password", user.getPassword());
        payload.put("phone", user.getPhone());
        payload.put("createTime", user.getCreateTime());
        payload.put("isDelete", user.getIsDelete());
        String token = JWTUtil.createToken(payload, SALT.getBytes());
        return token;
    }

    /**
     * 获取当前登录用户
     * 
     * @param request
     * @return
     */
    @Override
    public UserVO getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(SALT);
        if (user == null) {

        }
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setUserAccount(user.getUserAccount());
        userVO.setGender(user.getGender());
        userVO.setPhone(user.getPhone());
        return userVO;
    }

    /**
     * 根据用户名模糊匹配用户列表
     *
     * @param userQueryRequest
     * @return 匹配的用户列表
     */
    @Override
    public IPage<UserVO> queryUserListByUserNameLike(UserQueryRequest userQueryRequest) {
        return null;
    }

    /**
     * 更新用户信息
     * 
     * @param userUpdateRequest
     * @return
     */
    @Override
    @Transactional
    public boolean updateUserById(UserUpdateRequest userUpdateRequest) {
        return userMapper.UpdateUserById(userUpdateRequest.getId(), userUpdateRequest.getUsername(),
                userUpdateRequest.getPhone(), userUpdateRequest.getUserAccount(), userUpdateRequest.getGender(),
                userUpdateRequest.getPassword());
    }

    /**
     * 获取用户VO
     * 
     * @param user
     * @return
     */
    private UserVO getUserVO(UserRegisterRequest user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "user object is null");
        }
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Transactional
    public boolean deleteUserByAcount(String account) {
        return userMapper.DeleteByAcount(account) > 0;
    }

}
