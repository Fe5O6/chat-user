package com.h.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.h.model.admin.dtos.ApUserDto;
import com.h.model.admin.pojos.ApUser;
import com.h.model.common.dtos.ResponseResult;


/**
 * APP用户信息表
 * @author domainDrivenDesign
 */
public interface ApUserService extends IService<ApUser> {


    /**
     * 管理端登录
     * @param apUserDto
     * @return
     */
    ResponseResult login(ApUserDto apUserDto);
}
