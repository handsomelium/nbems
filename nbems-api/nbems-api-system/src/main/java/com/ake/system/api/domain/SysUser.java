package com.ake.system.api.domain;

import com.ake.nbems.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-13 15:41:46
 */
@Data
@NoArgsConstructor
@ApiModel(value="SysUser对象", description="用户信息")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "项目编号", required = true)
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    @ApiModelProperty(value = "用户账号", required = true)
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickName;

    @ApiModelProperty(value = "用户类型（00系统用户）")
    private String userType;

    @ApiModelProperty(value = "用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    @ApiModelProperty(value = "手机号码")
    @Size(min = 0, max = 20, message = "手机号码长度不能超过20个字符")
    private String phoneNumber;

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "证件类型")
    private String idType;

    @ApiModelProperty(value = "证件号码")
    private String idNumber;

    @ApiModelProperty(value = "地址")
    private String addr;

    @ApiModelProperty(value = "最后登陆IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登陆时间")
    private Date loginDate;

    @ApiModelProperty(value = "岗位组")
    @TableField(exist = false)
    private Long[] postIds;

    @ApiModelProperty(value = "角色组")
    @TableField(exist = false)
    private Long[] roleIds;

    @ApiModelProperty(value = "部门对象")
    @TableField(exist = false)
    private SysDept sysDept;

    @ApiModelProperty(value = "角色对象")
    @TableField(exist = false)
    private List<SysRole> roles;

    public boolean isAdmin() {
        return isAdmin(getId());
    }

    public static boolean isAdmin(Long id) {
        return id != null && 1L == id;
    }
}
