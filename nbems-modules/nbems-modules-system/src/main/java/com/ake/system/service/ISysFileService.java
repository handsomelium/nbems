package com.ake.system.service;

import com.ake.nbems.common.core.domain.R;
import com.ake.system.domain.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author yzt
 * @description
 * @date 2021/12/8 16:03
 */
public interface ISysFileService extends IService<SysFile> {
    /**
     * 上传文件
     * @param file
     * @throws
     * @return com.ake.system.domain.SysFile
     */
    R<SysFile> upload(MultipartFile file) throws Exception;
    /**
     * 文件下载
     * @param response
     * @param id
     * @throws
     * @return void
     */
    void downloadById(HttpServletResponse response, long id) throws Exception;
}
