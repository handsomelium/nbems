package com.ake.system.controller;

import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.system.domain.User;
import com.ake.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yezt
 * @description
 * @date 2021/12/23 15:44
 */
@Api(tags = {"测试用户"})
@RestController
@RequestMapping("/testuser")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试")
    @GetMapping("/list")
    public AjaxResult list() {
        return AjaxResult.success(userService.list());
    }


    @ApiOperation(value = "插入测试数据")
    @PostMapping("/insert")
    public AjaxResult insert(String dateStr) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");

        LinkedList<User> userList = new LinkedList<>();
        for (int i = 0; i < 1000; i ++){

            User user = new User();
            user.setId(i+1);
            user.setUsername("王老" + i + 1);
            user.setTotalAmount(new BigDecimal("9999"));
            user.setCreateTime(sdf.parse(dateStr));
            userList.add(user);

        }
        userService.saveBatch(userList);

        return AjaxResult.success("ok");

    }
}
