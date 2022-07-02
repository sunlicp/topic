package com.slcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.entity.User;
import com.slcp.mapper.IUserMapper;
import com.slcp.service.IUserService;
import org.springframework.stereotype.Service;


/**
 * @author Slcp
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {


    @Override
    public User getUser(String username){
        return this.lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public User getUserByTitle(String title) {
        return this.baseMapper.getUserByTitle(title);
    }

}
