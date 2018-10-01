package com.security.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryable {

    @ApiModelProperty(value = "This is user name")
    private String userName;

    @ApiModelProperty(value = "This is user age")
    private int age;

    @ApiModelProperty(value = "This is user future age")
    private int ageTo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
}
