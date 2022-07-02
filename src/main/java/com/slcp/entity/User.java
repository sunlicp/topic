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
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 782531244391633123L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @TableId(value = "id")
    private Long id;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @TableField(value = "picture")
    private String picture;
}
