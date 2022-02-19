package com.ake.nbems.ecms;

import com.ake.nbems.common.core.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lium
 * @Date 2021/12/8
 * @Description
 */
@RestController
@RequestMapping("/testt")
public class TestController {

    @GetMapping("testFeignClient")
    public R testFeignClient(String name){
        return R.ok(name + "  测试feign成功！");
    }

}
