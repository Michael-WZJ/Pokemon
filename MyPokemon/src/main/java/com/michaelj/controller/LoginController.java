package com.michaelj.controller;

import com.michaelj.domain.Code;
import com.michaelj.domain.Result;
import com.michaelj.domain.User;
import com.michaelj.service.UserService;
import com.michaelj.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User condition) {
        User user = userService.login(condition);

        // 用户名、密码正确，登录成功
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("group", user.getGroup());

            return new Result(Code.GET_OK.getCode(), JwtUtils.generateJwt(claims),"登录成功");
        }
        // 登录失败
        else {
            return new Result(Code.GET_ERR.getCode(), null, "用户名或密码错误");
        }
    }
}
