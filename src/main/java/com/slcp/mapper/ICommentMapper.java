package com.slcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.dto.CommentDTO;
import com.slcp.dto.CommentUserDTO;
import com.slcp.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @author Slcp
 */
public interface ICommentMapper extends BaseMapper<Comment> {

    /**
     * 查询评论
     * @param page 分页对象
     * @param title 标题
     * @return IPage
     */
    IPage<CommentDTO> getCommentList(IPage<CommentDTO> page, @Param("title") String title);

    /**
     * 获取评论用户
     * @param username 用户名
     * @return CommentUserDTO
     */
    CommentUserDTO getComUserByName(@Param("username") String username);

    /**
     * 获取用户信息
     * @param targetId 父id
     * @return CommentUserDTO
     */
    CommentUserDTO getComUserById(@Param("targetId") Long targetId);

    /**
     * 子评论查询
     * @param id 父id
     * @param title 标题
     * @return list
     */
    List<CommentDTO> getSubComList(@Param("id") Long id, @Param("title") String title);
}
