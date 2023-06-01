package com.h.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h.admin.mapper.ApUserMapper;
import com.h.admin.service.ApUserService;
import com.h.model.admin.dtos.ApUserDto;
import com.h.model.admin.pojos.ApUser;
import com.h.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * APP用户信息表
 * @author domainDrivenDesign
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Resource
    private ApUserMapper apUserMapper;

    /**
     * 管理端登录
     *
     * @param apUserDto
     * @return
     */
    @Override
    public ResponseResult login(ApUserDto apUserDto) {
        return null;
    }
}
