package com.user.Exception;


import com.user.common.BaseResponse;
import com.user.common.ErrorCode;
import com.user.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author zhexueqi
 * @ClassName GlobalExceptionHandler
 * @since 2024/3/6    20:02
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.info("runtimeException:{},{}",e.getMessage(),e);
        return ResultUtils.error(e.getCode(),"fail",e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse RuntimeExceptionHandler(RuntimeException e){
        log.info("runtimeException:{}",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"fail","");
    }



}
