package com.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.dto.User;
import com.security.dto.UserQueryable;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryable userQueryable, @PageableDefault(size = 10, page = 10, sort = "age,desc") Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(userQueryable, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User("admin", "password"));
        users.add(new User("admin1", "password1"));
        users.add(new User("admin2", "password2"));
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUser(@PathVariable String id){

        User user = new User("tom", "password");

        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach( error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach( error -> {
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable String id){
        System.out.println(id);
    }

}
