package com.michaelj.controller;

import com.michaelj.domain.Code;
import com.michaelj.domain.Result;
import com.michaelj.domain.entity.User;
import com.michaelj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public Result getAllUser() {
        List<User> userList = userService.getAll();
        int code = userList != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = userList != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, userList, msg);
    }
}
