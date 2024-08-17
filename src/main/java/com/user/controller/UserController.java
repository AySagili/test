package com.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.user.common.BaseResponse;
import com.user.model.dto.user.UserLoginRequest;
import com.user.model.dto.user.UserQueryRequest;
import com.user.model.dto.user.UserRegisterRequest;
import com.user.model.dto.user.UserUpdateRequest;
import com.user.model.vo.UserVO;
import com.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhexueqi
 * @ClassName UserController
 * @since 2024/8/15 16:27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册用户
     */
    @PostMapping("/register")
    public BaseResponse<Long> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        BaseResponse result = new BaseResponse<Long>(0, userService.registerUser(userRegisterRequest), "success", "1");
        return result;
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<String> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        BaseResponse result = new BaseResponse<String>(0, userService.userLogin(userLoginRequest), "success", "1");
        return result;
        // todo 登录
    }

    /**
     * 获取当前用户
     */
    @GetMapping("/get/current")
    public BaseResponse<UserVO> getCurrentUser(HttpServletRequest request) {
        // todo 获取当前用户
        UserVO userVO = userService.getLoginUser(request);

        return new BaseResponse<UserVO>(0, userVO, "success", "1");
    }

    /**
     * @dev 根据用户名分页模糊匹配用户列表(如果有则模糊匹配，如果没有则全量匹配)
     * @param userQueryRequest
     */
    @PostMapping("/list")
    @Operation(summary = "根据用户名模糊匹配用户列表")
    public BaseResponse<IPage<UserVO>> listUser(@RequestBody UserQueryRequest userQueryRequest) {
        // todo 模糊匹配用户列表
        IPage<UserVO> userPage = userService.queryUserListByUserNameLike(userQueryRequest);
        return null;
    }

    /**
     * 根据ID删除用户
     * 
     * @param id
     */
    @DeleteMapping("/delete")
    @Operation(summary = "根据ID删除用户")
    public BaseResponse<Boolean> deleteUser(@RequestBody String userAcount) {
        System.out.println(userAcount);
        Boolean b = userService.deleteUserByAcount(userAcount);

        return new BaseResponse<Boolean>(0, b, "success", "1");
        // todo 删除用户
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @Operation(summary = "修改用户")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        BaseResponse result = new BaseResponse<>(0, userService.updateUserById(userUpdateRequest), "success", "1");
        return result;
        // todo 修改用户
    }
}
