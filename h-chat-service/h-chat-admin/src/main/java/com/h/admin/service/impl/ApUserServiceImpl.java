package com.h.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h.admin.mapper.ApUserMapper;
import com.h.admin.service.ApUserService;
import com.h.model.admin.dtos.ApUserDto;
import com.h.model.admin.pojos.ApUser;
import com.h.model.common.dtos.ResponseResult;
import com.h.model.common.enums.AppHttpCodeEnum;
import com.h.uils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

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
        //参数校验
        if (apUserDto == null) {
            //响应失败
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //查询用户
        ApUser user = apUserMapper.selectOne(Wrappers.<ApUser>lambdaQuery()
                .eq(ApUser::getName, apUserDto.getName()));


        //判断
        if (user == null) {
            //响应失败
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //获取盐
        String salt = user.getSalt();

        //获取用户输入的密码
        String password = apUserDto.getPassword();

        //加密
        password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        //比对
        if (!password.equals(user.getPassword())){
            //失败
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //生成token
        String token = AppJwtUtil.getToken(user.getId().longValue());

        //关键信息设置为空
        user.setSalt("");
        user.setPassword("");

        //返回
        return ResponseResult.okResult(user);
    }
}
