package com.ake.nbems.eaps.controller;

import com.ake.common.log.annotation.Log;
import com.ake.common.log.enums.BusinessType;
import com.ake.nbems.api.ecms.service.TestFeignService;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.eaps.annotation.Autowiredd;
import com.ake.nbems.eaps.service.TestService;
import com.ake.nbems.eaps.service.impl.TestServiceImpl;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    @Autowiredd
    private TestService testService;



    @Autowired
    private TestFeignService testFeignService;

    public TestController() {

    }

    public TestService getTestService(){
        return testService;
    }


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
    public R<String> testFour(){
        return R.ok(testService.getUserInfo());
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("hh");
        list.add("孙悟空");
        list.forEach(System.out::println);


        Stream.of("123", "hh", "孙悟空").forEach(System.out::println);
    }
}
