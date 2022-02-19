package com.ake.common.datascope.annotation;

import java.lang.annotation.*;

/**
 * @author yezt
 * @description 数据权限过滤注解
 * @date 2021/12/21 9:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 部门表的别名
     */
    String deptAlias() default "";

    /**
     * 用户表的别名
     */
    String userAlias() default "";
}
