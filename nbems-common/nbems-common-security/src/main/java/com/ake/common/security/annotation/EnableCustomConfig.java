package com.ake.common.security.annotation;

import com.ake.common.security.config.ApplicationConfig;
import com.ake.common.security.config.SecurityImportBeanDefinitionRegistrar;
import com.ake.common.security.feign.OAuth2FeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.ake.**.mapper")
// 开启线程异步执行
@EnableAsync
// 自动加载类
@Import({ SecurityImportBeanDefinitionRegistrar.class, OAuth2FeignConfig.class, ApplicationConfig.class })
public @interface EnableCustomConfig {

}
