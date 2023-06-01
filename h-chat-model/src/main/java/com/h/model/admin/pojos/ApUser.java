package com.h.model.admin.pojos;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员用户信息表
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ApUser")
public class ApUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "密码、通信等加密盐")
    private String salt;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码,md5加密")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String image;

    @ApiModelProperty(value = "0 男	            1 女	            2 未知")
    private Integer sex;

    @ApiModelProperty(value = "0 未	            1 是")
    private Integer isCertification;

    @ApiModelProperty(value = "是否身份认证")
    private Boolean isIdentityAuthentication;

    @ApiModelProperty(value = "0正常	            1锁定")
    private Integer status;

    @ApiModelProperty(value = "0 普通用户	            1 自媒体人	            2 大V")
    private Integer flag;

    @ApiModelProperty(value = "注册时间")
    private Date createdTime;
}
