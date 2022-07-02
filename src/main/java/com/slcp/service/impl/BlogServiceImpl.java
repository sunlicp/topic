package com.slcp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.entity.*;
import com.slcp.mapper.IBlogMapper;
import com.slcp.service.IBlogService;
import com.slcp.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Slcp
 */
@Service
public class BlogServiceImpl extends ServiceImpl<IBlogMapper, Blog> implements IBlogService {

    @Override
    public boolean updateById(Blog blog) {
        this.baseMapper.updateById(blog);
        return false;
    }

    @Override
    public List<String> getLabelList(String username){
        return this.baseMapper.getLabelByUser(username);
    }

    @Override
    public List<Blog> loadBlogList(){
        return this.baseMapper.getBlogIntroduction();
    }

    @Override
    public List<String> loadHotLabel(){
        return this.baseMapper.getLabelByNum();
    }

    @Override
    public List<String> loadHotTitle(){
        return this.baseMapper.getTitleByNum();
    }

    @Override
    public IPage<Blog> loadBlogListByLabel(String label, Search search){
        IPage<Blog> page = this.lambdaQuery().eq(Blog::getLabel, label).page(SqlWhereWrapper.getIPage(search));
        page.getRecords().forEach(blog -> blog.setContentText(StringUtil.longFieldOmit(blog.getContentText())));
        return page;
    }

    @Override
    public IPage<Blog> loadStarBlog(String username, Search search){
        IPage<Blog> page = SqlWhereWrapper.getIPage(search);
        return this.baseMapper.getStarBlogList(page, username);
    }
}
