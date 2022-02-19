package com.ake.nbems.storageService.domain;

import lombok.Data;

/**
 * @author lyrstart
 * @create 2021-12-17 14:47
 */
@Data
public class Storage {
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
