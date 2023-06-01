package com.h.admin.controller;

import com.h.admin.service.ApUserService;
import com.h.model.admin.dtos.ApUserDto;
import com.h.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: domainDrivenDesign
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private ApUserService apUserService;

    @PostMapping("/in")
    public ResponseResult login(@Valid @RequestBody ApUserDto apUserDto){
        return apUserService.login(apUserDto);
    }
}
