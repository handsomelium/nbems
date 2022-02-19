package com.ake.system.controller;

import com.ake.nbems.common.core.web.controller.BaseController;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.nbems.common.core.web.page.TableDataInfo;
import com.ake.system.api.domain.SysOperLog;
import com.ake.system.service.ISysOperLogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author lium
 */
@RestController
@RequestMapping("/operlog")
public class SysOperlogController extends BaseController
{
    @Autowired
    private ISysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('system:operlog:list')")
    @GetMapping("/list")
    public TableDataInfo<SysOperLog> list(SysOperLog operLog)
    {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }



    @PostMapping
    public AjaxResult add(@RequestBody SysOperLog operLog)
    {
    	logger.info(JSON.toJSONString(operLog));
        return toAjax(operLogService.insertOperlog(operLog));
    }
    

}
