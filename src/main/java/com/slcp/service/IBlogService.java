package com.slcp.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.entity.Blog;
import com.slcp.entity.Search;

import java.util.List;

/**
 * @author Slcp
 */
public interface IBlogService extends IService<Blog> {

    /**
     * 根据标签，分页获取文章
     * @param label
     * @param search
     * @return
     */
    IPage<Blog> loadBlogListByLabel(String label, Search search);

    /**
     * 获取热门标签
     * @return list
     */
    List<String> loadHotLabel();

    /**
     * 获取热门标题
     * @return list
     */
    List<String> loadHotTitle();

    /**
     * 查询文章
     * @return list
     */
    List<Blog> loadBlogList();

    /**
     * 获取已有标签
     * @param username 用户名
     * @return string
     */
    List<String> getLabelList(String username);

    /**
     * 获取用户收藏文章
     * @param username 用户名
     * @param search 分页条件
     * @return IPage
     */
    IPage<Blog> loadStarBlog(String username, Search search);
}
