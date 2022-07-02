package com.slcp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.entity.BlogStar;

/**
 * @author Slcp
 */
public interface IStarService extends IService<BlogStar> {

    /**
     * 查看文章是否被用户收藏
     * @param title
     * @param username
     * @return
     */
    boolean existStar(String title, String username);
}
