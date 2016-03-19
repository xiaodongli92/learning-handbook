package com.learning.handbook.utils;

import com.learning.handbook.commons.AppConstants;
import com.learning.handbook.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xiaodong on 15/11/29.
 */
public class ValidateUtils {

    /**
     * 注册必填校验
     * @param user
     * @return
     */
    public static String signUpValidate(User user){
        if (StringUtils.isBlank(user.getName())){
            return AppConstants.NAME_NULL;
        }
        if (StringUtils.isBlank(user.getEmail())){
            return AppConstants.EMAIL_NULL;
        }
        if (StringUtils.isBlank(user.getPassword())){
            return AppConstants.PASSWORD_NULL;
        }
        return null;
    }

    /**
     * 登录校验
     * @param user
     * @return
     */
    public static String signInValidate(User user){
        if (StringUtils.isBlank(user.getEmail())){
            return AppConstants.EMAIL_NULL;
        }
        if (StringUtils.isBlank(user.getPassword())){
            return AppConstants.PASSWORD_NULL;
        }
        return null;
    }
}
