package com.ake.nbems.eaps.controller;

import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.eaps.domain.BillingOwner;
import com.ake.nbems.eaps.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lium
 * @Date 2022/7/11
 * @Description 事务测试类
 *
 * 如下方法中：
 * testTransaction加事务，insert1、insert2不加， 事务生效
 * testTransaction加事务，insert1、insert2加， 事务生效
 * testTransaction不加事务，insert1、insert2加， 事务不生效
 *
 * 事务失效场景：
 *
 * 1、数据库引擎不支持事务
 *
 * 2、没有被 Spring 管理 例如：没有@Service注解
 *
 * 3、方法不是 public 的
 *
 * 4、自身调用问题
 *
 * 5、数据源没有配置事务管理器
 *
 * 6、不支持事务（非实物运行）
 *
 * 7、捕获异常
 *
 * 8、异常类型错误
 */

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {


    @Autowired
    private TestMapper testMapper;

    @GetMapping("testTransaction")
    //@Transactional(rollbackFor = Exception.class)
    public R<?> testTransaction(){
        insert1();
        insert2();
        return R.ok("操作成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert1(){
        BillingOwner billingOwner = new BillingOwner();
        billingOwner.setOwnerCode("111");
        billingOwner.setOwnerName("张老五");
        testMapper.insert(billingOwner);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert2(){
        int i =  10/0;
        BillingOwner billingOwner = new BillingOwner();
        billingOwner.setOwnerCode("222");
        billingOwner.setOwnerName("张老六");
        testMapper.insert(billingOwner);
    }





}
