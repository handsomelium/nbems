package com.ake.system.controller;

import com.ake.nbems.common.core.web.controller.BaseController;
import com.ake.nbems.common.core.web.page.TableDataInfo;
import com.ake.system.api.domain.SysRole;
import com.ake.system.domain.query.SysRoleQuery;
import com.ake.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yezt
 * @description
 * @date 2021/12/14 9:04
 */
@Api(tags = {"角色管理"})
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/list")
    public TableDataInfo<SysRole> list(SysRoleQuery query) {
        startPage();
        return null;
    }

}
