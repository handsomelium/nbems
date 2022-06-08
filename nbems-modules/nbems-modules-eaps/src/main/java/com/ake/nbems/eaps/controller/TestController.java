package com.ake.nbems.eaps.controller;

import com.ake.common.log.annotation.Log;
import com.ake.common.log.enums.BusinessType;
import com.ake.nbems.api.ecms.service.TestFeignService;
import com.ake.nbems.eaps.domain.BillingOwner;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.domain.ResponseResult;
import com.ake.nbems.eaps.netty.util.ChannelMapUtils;
import com.ake.nbems.eaps.service.AsyncService;
import com.ake.nbems.eaps.service.TestService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lium
 * @Date 2021/12/6
 * @Description
 */
@Api(tags = {"测试"})
@RestController
@RefreshScope
@RequestMapping("/test")
@Slf4j
public class TestController {



    @Autowired
    private TestFeignService testFeignService;

    @Autowired
    private TestService testService;

    @Autowired
    private AsyncService asyncService;



    @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private Integer age;


    /**
     * 测试获取nacos配置中心的属性
     * @return
     */
    @ApiOperation("测试获取nacos配置中心的属性")
    @GetMapping("testOne")
    public R<?> testOne(){
        log.error("log is {}", "testOne");
        return R.ok("name=:"+ name + " age2=:" + age);
    }


    /**
     * 测试openFeign的服务调用，eaps调用ecms
     * @return
     */
    @GetMapping("testTwo")
    public R<?> testTwo(){
        return testFeignService.testFeignClient(name);
    }


    @Log(title = "测试保存日志", businessType = BusinessType.INSERT)
    @PostMapping("testThree")
    public R<?> testThree(){
        int i = 10 / 0;
        return R.ok("自定义日志注解");
    }


    @GetMapping("testFour")
    public R<?> testFour(){
        JSONObject obj = new JSONObject();
        obj.put("name", "张三");
        obj.put("age", 18);
        ChannelMapUtils.writeMessage("1", obj, 10);
        return R.ok("success!");
    }


    @GetMapping("testFive")
    public ResponseResult<List<BillingOwner>> testFive(){
        return ResponseResult.success(testService.getOwnerInfo());
    }


    @GetMapping("testSix")
    public ResponseResult<?> testSix(){
        for (int i = 0; i < 5; i++){
            asyncService.exect();
        }

        System.out.println("已执行完毕");
        return ResponseResult.success();
    }


}
