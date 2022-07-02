package com.slcp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Slcp
 */
@Data
@EqualsAndHashCode
@TableName(value = "blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = 781231244391633683L;
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
    @TableField(value = "content_text")
    private String contentText;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField(value = "title")
    private String title;
    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    @TableField(value = "label")
    private String label;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "user_name")
    private String userName;

    private Integer stars;
}
