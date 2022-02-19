package com.ake.system.controller;

import com.ake.nbems.common.core.domain.R;
import com.ake.system.domain.SysFile;
import com.ake.system.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yzt
 * @description 文件服务
 * @date 2021/12/8 16:04
 */
@Api(tags = {"文件服务"})
@RestController
@RequestMapping("/file")
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);
    @Autowired
    private ISysFileService ISysFileService;

    /**
     * 文件上传
     * @param file
     * @param request
     * @throws
     * @return com.ake.nbems.common.core.domain.R<com.ake.system.domain.SysFile>
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R<SysFile> upload(@RequestParam(name = "file",required = false) MultipartFile file, HttpServletRequest request) {
        try {
            return ISysFileService.upload(file);
        } catch (Exception e) {
            log.error("文件上传失败" + e);
            e.printStackTrace();
            return R.fail("文件上传失败");
        }
    }

    /**
     * 文件下载
     * @param response
     * @param id
     * @throws
     * @return void
     */
    @ApiOperation("文件下载")
    @GetMapping("/download/{id}")
    public void download(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
        ISysFileService.downloadById(response, id);
    }

}
