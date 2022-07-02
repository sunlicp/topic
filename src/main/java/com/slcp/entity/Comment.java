package com.slcp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2022-06-30 09:32:52
 */
@Data
@EqualsAndHashCode
@TableName(value = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 782531244391633683L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @TableId(value = "id")
    private Long id;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @TableField(value = "username")
    private String username;
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    @TableField(value = "father_id")
    private Long fatherId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date")
    private String  createDate;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField(value = "title")
    private String title;
    /**
     * 目标id
     */
    @ApiModelProperty(value = "目标id")
    @TableField(value = "target_id")
    private Long targetId;
}

