package com.xybl.server.controller;

import com.xybl.server.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserController
 * <p></p>
 * @author liubocai
 * @create 2021/2/3
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;
}
