package com.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.validator.MyConstraint;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    public interface  UserSimpleView{}

    public interface  UserDetailView extends UserSimpleView {}

    @MyConstraint(message = "name has to be tom")
    private String userName;

    @NotBlank(message = "password can not be empty")
    private String password;

    private String id;

    @Past(message = "birthday has to be a past date")
    private Date birthday;

    public User(){

    }
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
