package com.ake.system.domain.query;

import com.ake.nbems.common.core.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezt
 * @description
 * @date 2021/12/21 14:26
 */
@Data
@ApiModel(value="SysRole对象", description="角色信息查询实体")
public class SysRoleQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色权限字符串")
    private String roleKey;

    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private String status;
}
