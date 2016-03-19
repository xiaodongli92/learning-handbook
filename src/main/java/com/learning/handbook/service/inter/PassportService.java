package com.learning.handbook.service.inter;

import com.learning.handbook.model.User;
import com.learning.handbook.model.UserInfo;

/**
 * Created by xiaodong on 2015/11/16.
 */
public interface PassportService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    String signUp(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    String signIn(User user);

    /**
     * 根据邮箱得到user
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 更新数据
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 根据id得到用户基本信息
     * @param userId
     * @return
     */
    User getUserById(long userId);

    /**
     * 根据userId获得用户个人资料
     * @param userId
     * @return
     */
    UserInfo getUserInfoByUserId(long userId);

    /**
     * 更新用户个人资料
     * @param userInfo
     * @return
     */
    String updateUserInfo(UserInfo userInfo);
}
