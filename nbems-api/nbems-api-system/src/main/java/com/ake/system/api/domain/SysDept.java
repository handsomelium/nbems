package com.ake.system.api.domain;

import com.ake.nbems.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-14 16:23:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysDept对象", description="部门表")
public class SysDept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父部门id")
    private Long parentId;

    @ApiModelProperty(value = "关联项目编号")
    private String projectCode;

    @ApiModelProperty(value = "是否项目主部门")
    private Integer isProjectMain;

    @ApiModelProperty(value = "祖级列表")
    private String ancestors;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "负责人")
    private String leader;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "部门状态（0正常 1停用）")
    private String status;
}
