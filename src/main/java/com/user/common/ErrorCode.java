package com.user.common;


/**
 * @author zhexueqi
 * @ClassName ErrorCode
 * @since 2024/3/6    19:20
 */
public enum ErrorCode {


    PARAMS_ERROR(40000, "参数错误", ""),
    NULL_ERROR(40001,"请求数据为空",""),
    NO_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    UPDATE_USER_NULL(40102,"更新用户为空",""),
    USER_NOT_EXIST(40103,"用户不存在",""),
    UNAUTHORIZED_ERROR(40105, "未授权", ""),
    SYSTEM_ERROR(50000,"系统内部异常",""),
    UPLOAD_ERROR(50010, "上传失败", ""),
    LOCK_ACQUIRE_FAILED(50100, "获取锁失败", ""),
    TOKEN_NOT_FOUND(50001, "token不存在", ""),
    INVALID_TOKEN(50002, "token无效","" ),
    UPDATE_ERROR(40104, "更新失败", "");

    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
