package com.learning.handbook.controller;

import com.learning.handbook.model.User;
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
import java.util.Date;

/**
 * Created by xiaodong on 2015/11/13.
 */
@Controller
@RequestMapping("")
public class PassportController {

    private static final Logger LOG = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private PassportService passportService;

    @RequestMapping("goSignUp")
    public String goSignUp(){
        return "signUp";
    }

    @RequestMapping("goSignIn")
    public String goSignIn(){
        return "signIn";
    }

    @ResponseBody
    @RequestMapping("signUp")
    public String signUp(User user){
        try {
            user.setRegisterDate(new Date());
            String errMsg = passportService.signUp(user);
            if (errMsg!=null){
                return JsonResponseUtils.badResult(errMsg);
            }
            LOG.info("new {}",user);
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("error,",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("signIn")
    public String signIn(User user,HttpServletRequest request){
        try {
            String errMsg = passportService.signIn(user);
            if (errMsg!=null){
                return JsonResponseUtils.badResult(errMsg);
            }
            User userSession = passportService.getUserByEmail(user.getEmail());
            CommonsUtils.setSession(request,userSession);
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("error:",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @RequestMapping("signOut")
    public String signOut(HttpServletRequest request) {
        try {
            CommonsUtils.removeAllSession(request);
        } catch (Exception e) {
            LOG.error("sign out error",e);
        }
        return "redirect:goSignIn.do";
    }
}
