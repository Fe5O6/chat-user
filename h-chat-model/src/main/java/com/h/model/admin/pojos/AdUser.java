package com.h.model.admin.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("ad_user")
@ApiModel(value="AdUser", description="管理员用户信息表")
public class AdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "头像")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "状态	            0 暂时不可用	            1 永久不可用	            9 正常可用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private Date createdTime;

    /**
     * 状态枚举类
     */
    @Alias("AdUserStatus")
    public enum Status{
        /**
         * 正常使用
         */
        NORMAL(9),
        /**
         * 永久不可用
         */
        FOREVER_NO_USE(1),
        /**
         * 暂时不可用
         */
        TIMEOUT_NO_USE(0);

        Integer code;
        Status(Integer code){
            this.code = code;
        }
        public Integer getCode(){
            return this.code;
        }
    }

}
