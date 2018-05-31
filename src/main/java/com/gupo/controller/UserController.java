package com.gupo.controller;

import com.gupo.domain.User;
import com.gupo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public User getUserInfo(@RequestParam("id") Integer id){
        return userService.getUser(id);
    }
}