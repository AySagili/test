package com.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.user.Exception.BusinessException;
import com.user.common.ErrorCode;

import java.util.Calendar;
import java.util.Map;

import static com.user.constant.JwtConstant.JWT_HEADER;

public class JWTUtils {
    private static String SIGNATURE = "token!@#$%^7890";

    /**
     * 生成token
     * @param id //传入payload
     * @return 返回token
     */
    public static String getToken(long id){
        JWTCreator.Builder builder = JWT.create().withClaim(JWT_HEADER,id);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,24);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGNATURE)).toString();
    }

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static long getToken(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        // 验证JWT_HEADER是否存在
        if (!claims.containsKey(JWT_HEADER)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_ERROR, "User not logged in");
        }
        // 获取userId
        Long userId = claims.get(JWT_HEADER).asLong();
        // 校验userId是否有效
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Invalid user ID");
        }
        return userId;
    }
}
