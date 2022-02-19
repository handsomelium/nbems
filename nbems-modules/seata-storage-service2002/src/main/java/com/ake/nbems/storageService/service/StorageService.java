package com.ake.nbems.storageService.service;

/**
 * @author lyrstart
 * @create 2021-12-17 14:52
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
