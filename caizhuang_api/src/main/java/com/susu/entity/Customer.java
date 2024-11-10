package com.susu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Customer {
    private Integer id;

    private String userName;

    private String password;

    private String realName;

    private String mobile;

    private String passwordQuestion;

    private String passwordHintAnswer;

    private String  img;

    private int gender;

    private String oldPsw;

    private String token;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPsw() {
        return oldPsw;
    }

    public void setOldPsw(String oldPsw) {
        this.oldPsw = oldPsw;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion == null ? null : passwordQuestion.trim();
    }

    public String getPasswordHintAnswer() {
        return passwordHintAnswer;
    }

    public void setPasswordHintAnswer(String passwordHintAnswer) {
        this.passwordHintAnswer = passwordHintAnswer == null ? null : passwordHintAnswer.trim();
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", passwordQuestion='" + passwordQuestion + '\'' +
                ", passwordHintAnswer='" + passwordHintAnswer + '\'' +
                ", img='" + img + '\'' +
                ", gender=" + gender +
                ", oldPsw='" + oldPsw + '\'' +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
