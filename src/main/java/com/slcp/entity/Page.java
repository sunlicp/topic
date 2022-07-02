package com.slcp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Slcp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable{
    private static final long serialVersionUID = 122531244391633683L;
    private Integer total;
    private T value;
}
