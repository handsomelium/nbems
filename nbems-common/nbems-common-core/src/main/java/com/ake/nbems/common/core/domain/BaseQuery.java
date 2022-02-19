package com.ake.nbems.common.core.domain;

import com.ake.nbems.common.core.enums.DelFlagEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezt
 * @description 基础查询类
 * @date 2021/12/14 16:51
 */
@Data
public class BaseQuery implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "开始时间")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "参数集合")
    private Map<String, Object> params;

    @ApiModelProperty(value = "删除标志（0代表存在 1代表删除） 默认为0")
    private String delFlag = DelFlagEnum.NORMAL.getValue();

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
