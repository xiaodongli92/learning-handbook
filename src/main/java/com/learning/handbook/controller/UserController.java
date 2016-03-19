package com.learning.handbook.controller;

import com.learning.handbook.interceptor.AuthPermission;
import com.learning.handbook.model.User;
import com.learning.handbook.model.UserInfo;
import com.learning.handbook.service.inter.PassportService;
import com.learning.handbook.utils.CommonsUtils;
import com.learning.handbook.utils.JsonResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaodong on 2016/3/19.
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PassportService passportService;

    @AuthPermission
    @RequestMapping("userInfo")
    public String userInfo(HttpServletRequest request){
        try {
            Long userId = CommonsUtils.getUserIdFromSession(request);
            User user = passportService.getUserById(userId);
            UserInfo userInfo = passportService.getUserInfoByUserId(userId);
            LOG.info(":{}",user);
            LOG.info(":{}",userInfo);
            request.setAttribute("user",user);
            request.setAttribute("userInfo",userInfo);
            return "userInfo";
        } catch (Exception e){
            LOG.error("获取用户信息失败",e);
            request.setAttribute("errMsg",e.getMessage());
            return "error";
        }
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("saveUserInfo")
    public String saveUserInfo(UserInfo userInfo,HttpServletRequest request) {
        try {
            userInfo.setUserId(CommonsUtils.getUserIdFromSession(request));
            String errMsg = passportService.updateUserInfo(userInfo);
            if (errMsg!=null){
                return JsonResponseUtils.badResult(errMsg);
            }
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("保存用户个人资料失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("saveUser")
    public String saveUser(User user,HttpServletRequest request){
        try {
            user.setId(CommonsUtils.getUserIdFromSession(request));
            passportService.update(user);
            CommonsUtils.setSession(request,user);
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("保存用户基本信息失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }
}
