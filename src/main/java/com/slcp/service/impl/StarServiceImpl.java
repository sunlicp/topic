package com.slcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.entity.BlogStar;
import com.slcp.mapper.IStarMapper;
import com.slcp.service.IStarService;
import org.springframework.stereotype.Service;


/**
 * @author Slcp
 */
@Service
public class StarServiceImpl extends ServiceImpl<IStarMapper, BlogStar> implements IStarService {

    @Override
    public boolean existStar(String title, String username) {
        return this.lambdaQuery().eq(BlogStar::getBlogTitle, title).eq(BlogStar::getUsername, username).count() >= 1;
    }
}
