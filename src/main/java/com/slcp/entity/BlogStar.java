package com.slcp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (BlogStar)实体类
 *
 * @author makejava
 * @since 2022-07-01 13:59:26
 */
@Data
@EqualsAndHashCode
@TableName(value = "blog_star")
public class BlogStar implements Serializable {
    private static final long serialVersionUID = -463123123852717312L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @TableId(value = "id")
    private Long id;
    /**
     * 博客名称
     */
    @ApiModelProperty(value = "博客名称")
    @TableField(value = "blog_title")
    private String blogTitle;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "username")
    private String username;
}

