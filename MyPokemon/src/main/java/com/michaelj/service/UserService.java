package com.michaelj.service;

import com.michaelj.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    /**
     * 查找全部用户信息
     * @return
     */
    List<User> getAll();

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);
}
