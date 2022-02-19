package com.ake.system.domain;

import com.ake.nbems.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-14 10:06:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysPost对象", description="岗位信息表")
public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "显示顺序")
    private Integer postSort;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;
}
