package com.slcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slcp.entity.User;

/**
 * @author Slcp
 */
public interface IUserMapper extends BaseMapper<User> {

    /**
     * 查询用户
     * @param title 标题
     * @return User
     */
    User getUserByTitle(String title);

}
