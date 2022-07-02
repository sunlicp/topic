package com.slcp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.dto.CommentDTO;
import com.slcp.entity.Comment;
import com.slcp.entity.Search;
import com.slcp.entity.SqlWhereWrapper;
import com.slcp.mapper.ICommentMapper;
import com.slcp.service.ICommentService;
import com.slcp.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Slcp
 */
@Service
public class CommentServiceImpl extends ServiceImpl<ICommentMapper, Comment> implements ICommentService {

    @Resource
    private IUserService userService;


    @Override
    public IPage<CommentDTO> loadComment(String title , Search search){
        IPage<CommentDTO> page = SqlWhereWrapper.getIPage(search);
        IPage<CommentDTO> commentList = this.baseMapper.getCommentList(page, title);
        return commentList;
    }

    @Override
    public int getTotalNum(String title){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Comment::getTitle, title);
        return this.count(queryWrapper);
    }
}
