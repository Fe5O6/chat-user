package com.h.model.admin.dtos;

import lombok.Data;

/**
 * 管理员用户信息dto
 */
@Data
public class AdUserDto {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
