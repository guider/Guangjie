package com.yanyuanquan.model.entity;

/**
 * Created by Quinn on 8/1/15.
 * 账号密码错误抛出该异常
 * statusCode 401
 */
public class AuthError extends Exception {

    public AuthError(String exceptionMessage) {
        super(exceptionMessage);
    }
}
