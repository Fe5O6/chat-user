package com.h.admin.annotation;


import java.lang.annotation.*;

/**
 * 自定义平台管理端操作日志记录注解
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {
    /**
     * 操作--》新增、删除、修改
     * @return
     */
    String type() default "";

    /**
     * 模块
     * @return
     */
    String module() default "";
}
