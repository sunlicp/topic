package com.slcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Slcp
 */
public interface IBlogMapper extends BaseMapper<Blog> {

    /**
     * 获取已有标签
     * @param username 用户名
     * @return string
     */
    List<String> getLabelByUser(String username);

    /**
     * 查询文章
     * @return list
     */
    List<Blog> getBlogIntroduction();

    /**
     * 获取热门标签
     * @return string
     */
    List<String> getLabelByNum();

    /**
     * 获取热门标题
     * @return list
     */
    List<String> getTitleByNum();

    /**
     * 获取用户收藏文章
     * @param page 分页
     * @param username 用户名
     * @return IPage
     */
    IPage<Blog> getStarBlogList(IPage<Blog> page, @Param("username") String username);
}
