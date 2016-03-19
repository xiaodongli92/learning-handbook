package com.learning.handbook.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xiaodong on 2016/2/1.
 *
 * 个人资料
 */
@Entity
@Table(name="USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    /**
     * 用户ID
     */
    @Column(unique = true)
    private long userId;

    /**
     * 所在省份编码
     */
    @Column(length = 6)
    private String provinceCode;

    /**
     * 所在城市编码
     */
    @Column(length = 6)
    private String cityCode;

    /**
     * 所在区县编码
     */
    @Column(length = 6)
    private String countyCode;

    /**
     * 性别 1 男 2女
     */
    @Column(length = 4)
    private Integer gender;

    /**
     * 性取向 1 男 2女
     */
    @Column(length = 4)
    private Integer sexualOrientation;

    /**
     * 感情状况 1 单身 2 求交往 3 暗恋中 4 暧昧中 5 恋爱中 6 订婚 7 已婚 8 分居 9 离异 10 丧偶 99 其他
     */
    @Column(length = 4)
    private Integer maritalStatus;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date birthday;

    /**
     * 血型
     */
    @Column(length = 4)
    private Integer bloodType;

    /**
     * 简介
     */
    @Column(length = 200)
    private String profile;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", gender=" + gender +
                ", sexualOrientation=" + sexualOrientation +
                ", maritalStatus=" + maritalStatus +
                ", birthday=" + birthday +
                ", bloodType=" + bloodType +
                ", profile='" + profile + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(Integer sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getBloodType() {
        return bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
