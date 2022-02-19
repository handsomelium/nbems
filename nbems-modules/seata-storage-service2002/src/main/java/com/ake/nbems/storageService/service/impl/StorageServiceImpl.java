package com.ake.nbems.storageService.service.impl;

import com.ake.nbems.storageService.dao.StorageDao;
import com.ake.nbems.storageService.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lyrstart
 * @create 2021-12-17 14:52
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);

        int i=10/0;
        LOGGER.info("------->storage-service中扣减库存结束");


    }
}
