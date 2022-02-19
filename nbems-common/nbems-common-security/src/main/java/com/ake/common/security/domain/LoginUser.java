package com.ake.common.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 登录用户身份权限
 * 
 * @author guojm
 */

public class LoginUser extends User {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 项目编码
     */
    private String projectCode;
    
    /**
     * 是否微信
     */
    private Boolean isWx;
    
    /**
     * 是否客服系统
     */
    private Boolean isCustomer;
    
    public LoginUser(Long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                     boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String projectCode, boolean isWx, boolean isCustomer) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.projectCode = projectCode;
        this.isWx = isWx;
        this.isCustomer = isCustomer;
    }

    public LoginUser(Long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                     boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Boolean getIsWx() {
		return isWx;
	}

	public void setIsWx(Boolean isWx) {
		this.isWx = isWx;
	}

	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

    @Override
    public String toString() {
        return "LoginUser{" +
                "userId=" + userId +
                ", projectCode='" + projectCode + '\'' +
                ", isWx=" + isWx +
                ", isCustomer=" + isCustomer +
                '}';
    }
}
