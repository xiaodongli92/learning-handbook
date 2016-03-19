package com.learning.handbook.dao;

import com.learning.handbook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by xiaodong on 15/11/29.
 */
public interface PassportDAO extends CrudRepository<User,Long> {

    @Query(value = "SELECT * FROM user WHERE name=?",nativeQuery = true)
    User getUserByName(String name);

    @Query(value = "SELECT * FROM user WHERE email=?",nativeQuery = true)
    User getUserByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE email=? and password=?",nativeQuery = true)
    User signInValidate(String email, String password);

    @Query(value = "SELECT * FROM user WHERE id=?",nativeQuery = true)
    User getUserById(long userId);

}
