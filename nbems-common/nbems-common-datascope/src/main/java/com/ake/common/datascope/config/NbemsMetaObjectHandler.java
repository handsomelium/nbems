package com.ake.common.datascope.config;

import com.ake.common.security.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yzt
 * @description 数据填充实现
 * @date 2021/12/10 9:55
 */
@Component
public class NbemsMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        if(SecurityUtils.getUsername()!=null) {
            this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getUsername());
        } else {
            this.strictInsertFill(metaObject, "createBy", String.class, "admin");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        if(SecurityUtils.getUsername()!=null) {
            this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getUsername());
        } else {
            this.strictUpdateFill(metaObject, "updateBy", String.class, "admin");
        }
    }
}
