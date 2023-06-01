package com.h.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h.model.admin.pojos.ApUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * APP用户信息表 Mapper
 * @author domainDrivenDesign
 */
@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {

}
