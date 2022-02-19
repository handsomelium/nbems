package com.ake.nbems.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yezt
 * @description 数据状态
 * @date 2021/12/22 10:02
 */
@Getter
@AllArgsConstructor
public enum DelFlagEnum {
    /**
     * 0：存在，1：删除
     */
    NORMAL("0"),
    DELETED("1");
    private String value;

}
