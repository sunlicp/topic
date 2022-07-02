package com.slcp.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.dto.CommentDTO;
import com.slcp.entity.Comment;
import com.slcp.entity.Page;
import com.slcp.entity.Search;

/**
 * @author Slcp
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 根据标题查询数量
     * @param title 标题
     * @return int
     */
    int getTotalNum(String title);

    /**
     * 评论
     * @param title 标题
     * @param search 分页条件
     * @return IPage
     */
    IPage<CommentDTO> loadComment(String title, Search search);
}
