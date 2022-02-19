package com.ake.nbems.api.ecms.service;

import com.ake.nbems.common.core.constant.ServiceNameConstants;
import com.ake.nbems.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author lium
 * @Date 2021/12/8
 * @Description
 */
@FeignClient(contextId = "TestFeignService", value = ServiceNameConstants.ECMS_SERVICE)
public interface TestFeignService {

    @GetMapping("/ecms/testt/testFeignClient")
    public R testFeignClient(@RequestParam("name") String name);
}
