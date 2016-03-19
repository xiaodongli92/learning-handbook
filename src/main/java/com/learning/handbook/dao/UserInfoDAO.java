package com.learning.handbook.dao;

import com.learning.handbook.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by xiaodong on 2016/2/1.
 */
public interface UserInfoDAO extends CrudRepository<UserInfo,Long> {

    @Query(value = "SELECT * FROM user_info WHERE user_id=?",nativeQuery = true)
    UserInfo getUserInfoByUserId(long id);
}
