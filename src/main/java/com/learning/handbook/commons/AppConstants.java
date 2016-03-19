package com.learning.handbook.commons;

/**
 * Created by xiaodong on 15/11/29.
 */
public class AppConstants {

    private AppConstants(){

    }

    public static final String NAME_NULL = "your name should not be null";//姓名为空

    public static final String EMAIL_NULL = "your email should not be null";//邮箱为空

    public static final String PASSWORD_NULL = "password should not be null";//密码为空

    public static final String ALREADY_SIGN_UP = "This email has been registered";//邮箱早已经注册

    public static final String ERR_EMIAL_PASSWORD = "email or password is incorrent";//邮箱或密码不对

    public static final String NOT_SIGN_UP = "The email is not registered";//邮箱未注册

    public static final String SESSION_USER_NAME = "userName";

    public static final String SESSION_EMAIL = "email";

    public static final String SESSION_USER_ID = "userId";
}
