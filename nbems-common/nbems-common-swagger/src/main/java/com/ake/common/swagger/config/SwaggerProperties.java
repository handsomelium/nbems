package com.ake.common.swagger.config;

import io.swagger.annotations.Authorization;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzt
 * @description
 * @date 2021/12/10 11:11
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerProperties {
    /**
     * 是否开启swagger
     */
    private Boolean enabled;
    /**
     * swagger解析包的路径
     */
    private String basePackage = "";
    /**
     * 标题
     */
    private String title = "";
    /**
     * 描述
     */
    private String description = "";
    /**
     * 版本
     */
    private String version = "";
    /**
     * 许可证
     */
    private String license = "";
    /**
     * 许可证URL
     */
    private String licenseUrl = "";
    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";
    /**
     * 全局统一鉴权配置
     **/
    private Authorization authorization = new Authorization();

    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();

    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();

    public static class Authorization
    {
        /**
         * 鉴权策略ID，需要和SecurityReferences ID保持一致
         */
        private String name = "";

        /**
         * 需要开启鉴权URL的正则
         */
        private String authRegex = "^.*$";

        /**
         * 鉴权作用域列表
         */
        private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();

        private List<String> tokenUrlList = new ArrayList<>();

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getAuthRegex()
        {
            return authRegex;
        }

        public void setAuthRegex(String authRegex)
        {
            this.authRegex = authRegex;
        }

        public List<AuthorizationScope> getAuthorizationScopeList()
        {
            return authorizationScopeList;
        }

        public void setAuthorizationScopeList(List<AuthorizationScope> authorizationScopeList)
        {
            this.authorizationScopeList = authorizationScopeList;
        }

        public List<String> getTokenUrlList()
        {
            return tokenUrlList;
        }

        public void setTokenUrlList(List<String> tokenUrlList)
        {
            this.tokenUrlList = tokenUrlList;
        }
    }

    public static class AuthorizationScope
    {
        /**
         * 作用域名称
         */
        private String scope = "";

        /**
         * 作用域描述
         */
        private String description = "";

        public String getScope()
        {
            return scope;
        }

        public void setScope(String scope)
        {
            this.scope = scope;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }
    }
}
