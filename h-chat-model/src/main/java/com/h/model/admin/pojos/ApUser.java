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

    @ApiModelProperty(value = "登录用户名")
    private String name;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String image;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态	            0 暂时不可用	            1 永久不可用	            9 正常可用")
    private Integer status;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "最后一次登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 状态枚举类
     */
    @Alias("ApUserStatus")

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
