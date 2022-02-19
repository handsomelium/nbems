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
 * @Date 2021-12-08 17:13:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysFile对象", description="文件服务")
public class SysFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "存储桶")
    private String bucketName;

    @ApiModelProperty(value = "存储前缀")
    private String prefix;

    @ApiModelProperty(value = "文件原始名称")
    private String originalName;

    @ApiModelProperty(value = "文件存储名称")
    private String storeName;

    @ApiModelProperty(value = "小图名称")
    private String miniName;

    @ApiModelProperty(value = "HTTP-contentType")
    private String contentType;

    @ApiModelProperty(value = "后缀名")
    private String suffix;

    @ApiModelProperty(value = "文件大小（单位：b）")
    private Long fileSize;

    @ApiModelProperty(value = "文件大小中文")
    private String fileSizeName;

    @ApiModelProperty(value = "下载次数")
    private Integer downloads;


}
