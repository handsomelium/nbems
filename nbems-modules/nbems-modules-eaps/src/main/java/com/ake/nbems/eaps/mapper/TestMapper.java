package com.ake.nbems.eaps.mapper;

import com.ake.nbems.eaps.domain.BillingOwner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lium
 * @Date 2022/4/8
 * @Description
 */
@Mapper
public interface TestMapper extends BaseMapper<BillingOwner> {


    int insertOwnerInfo(BillingOwner billingOwner);


}
