package com.slcp.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Slcp
 */
@Data
public class CommentUserDTO implements Serializable {
    private static final long serialVersionUID = 542131244391633683L;
    private Integer id;
    private String nickName;
    private String avatar;
}
