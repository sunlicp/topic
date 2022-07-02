package com.slcp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.api.Result;
import com.slcp.constant.DevOpsConstant;
import com.slcp.dto.CommentDTO;
import com.slcp.entity.Comment;
import com.slcp.entity.Search;
import com.slcp.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Slcp
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/comment")
@Api(value = "评论页查询接口", tags = "评论页查询接口")
public class CommentController {

    private final ICommentService commentService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "saveComment", notes = "保存评论")
    public Result<Comment> saveComment(@RequestBody Comment comment) {
        comment.setCreateDate(new SimpleDateFormat(DevOpsConstant.DATETIME_FORMAT).format(new Date()));
        commentService.save(comment);
        return Result.data(comment);
    }

    @GetMapping("/load")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadCommentList", notes = "获取所有评论")
    public Result<IPage<CommentDTO>> loadCommentList(@RequestParam("title") String title, Search search) {
        return Result.data(commentService.loadComment(title, search));
    }

    @GetMapping("/totalNum")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadTotalComNum", notes = "获取评论数")
    public Result<Integer> loadTotalComNum(String title) {
        return Result.data(commentService.getTotalNum(title));
    }
}
