package com.ake.system.domain.query;

import com.ake.nbems.common.core.domain.BaseQuery;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezt
 * @description
 * @date 2021/12/14 16:45
 */
@ApiModel(description = "用户信息")
@Data
public class SysUserQuery extends BaseQuery {
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "证件类型")
    private String idType;

    @ApiModelProperty(value = "证件号码")
    private String idNumber;

    @ApiModelProperty(value = "地址")
    private String addr;

    @ApiModelProperty(value = "岗位组")
    private Long[] postIds;

    @ApiModelProperty(value = "角色组")
    private Long[] roleIds;
}
