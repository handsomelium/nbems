package com.ake.nbems.common.core.domain;

import com.ake.nbems.common.core.enums.DelFlagEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yezt
 * @description 基础VO
 * @date 2021/12/14 11:15
 */
@Data
public class BaseVO implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除标志（0代表存在 1代表删除） 默认为0")
    private String delFlag = DelFlagEnum.NORMAL.getValue();
}
