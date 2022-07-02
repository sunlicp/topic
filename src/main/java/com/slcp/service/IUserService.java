package com.slcp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.entity.User;

/**
 * @author Slcp
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户
     * @param username 用户名
     * @return user
     */
    User getUser(String username);

    /**
     * 查询用户
     * @param title
     * @return
     */
    User getUserByTitle(String title);
}
