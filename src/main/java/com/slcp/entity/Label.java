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
@TableName(value = "label")
public class Label implements Serializable {
    private static final long serialVersionUID = 12311244391633683L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @TableId(value = "id")
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;
}
