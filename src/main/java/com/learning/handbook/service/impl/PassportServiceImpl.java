package com.learning.handbook.service.impl;

import com.learning.handbook.commons.AppConstants;
import com.learning.handbook.dao.PassportDAO;
import com.learning.handbook.dao.UserInfoDAO;
import com.learning.handbook.model.User;
import com.learning.handbook.model.UserInfo;
import com.learning.handbook.service.inter.PassportService;
import com.learning.handbook.utils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaodong on 2015/11/16.
 */
@Service
public class PassportServiceImpl implements PassportService {

    private static final Logger LOG = LoggerFactory.getLogger(PassportServiceImpl.class);

    @Autowired
    private PassportDAO passportDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public String signUp(User user) {
        String validateMsg = ValidateUtils.signUpValidate(user);
        if (validateMsg != null){
            return validateMsg;
        }
        if (passportDAO.getUserByEmail(user.getEmail())!=null){
            return AppConstants.ALREADY_SIGN_UP;
        }
        passportDAO.save(user);
        return null;
    }

    @Override
    public String signIn(User user) {
        String validateMsg = ValidateUtils.signInValidate(user);
        if (validateMsg!=null){
            return validateMsg;
        }
        if (passportDAO.getUserByEmail(user.getEmail())==null){
            return AppConstants.NOT_SIGN_UP;
        }
        if (passportDAO.signInValidate(user.getEmail(), user.getPassword())==null){
            return AppConstants.ERR_EMIAL_PASSWORD;
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return passportDAO.getUserByEmail(email);
    }

    @Override
    public User update(User user) {
        User oldUser = passportDAO.getUserById(user.getId());
        oldUser.setName(user.getName());
        oldUser.setRealName(user.getRealName());
        oldUser.setMobile(user.getMobile());
        return passportDAO.save(oldUser);
    }

    @Override
    public User getUserById(long userId) {
        return passportDAO.getUserById(userId);
    }

    @Override
    public UserInfo getUserInfoByUserId(long userId) {
        return userInfoDAO.getUserInfoByUserId(userId);
    }

    @Override
    public String updateUserInfo(UserInfo userInfo) {
        User user = passportDAO.getUserById(userInfo.getUserId());
        if (user == null) {
            return "不存在这个用户";
        }
        UserInfo oldUserInfo = userInfoDAO.getUserInfoByUserId(userInfo.getUserId());
        oldUserInfo = initUpdateUserInfo(oldUserInfo,userInfo);
        userInfoDAO.save(oldUserInfo);
        return null;
    }

    private static UserInfo initUpdateUserInfo(UserInfo oldUserInfo,UserInfo userInfo){
        if (oldUserInfo == null){//第一次保存个人信息
            oldUserInfo = new UserInfo();
            oldUserInfo.setUserId(userInfo.getUserId());
        }
        oldUserInfo.setProvinceCode(userInfo.getProvinceCode());
        oldUserInfo.setCityCode(userInfo.getCityCode());
        oldUserInfo.setCountyCode(userInfo.getCountyCode());
        oldUserInfo.setGender(userInfo.getGender());
        oldUserInfo.setSexualOrientation(userInfo.getSexualOrientation());
        oldUserInfo.setMaritalStatus(userInfo.getMaritalStatus());
        oldUserInfo.setBirthday(userInfo.getBirthday());
        oldUserInfo.setBloodType(userInfo.getBloodType());
        oldUserInfo.setProfile(userInfo.getProfile());
        return oldUserInfo;
    }
}
