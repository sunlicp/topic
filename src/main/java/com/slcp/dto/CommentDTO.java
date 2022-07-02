package com.slcp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Slcp
 */
@Data
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = -1509782578272943999L;
    private Long id;
    private String content;
    private String username;
    private Long fatherId;
    private String createDate;
    private String title;
    private Long targetId;
    private CommentUserDTO commentUser;
    private CommentUserDTO targetUser;
    private List<CommentDTO> childrenList;
}
