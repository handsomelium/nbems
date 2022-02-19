package com.ake.system.service.impl;

import com.ake.common.security.utils.SecurityUtils;
import com.ake.nbems.common.core.constant.MinioConstants;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.utils.DateUtils;
import com.ake.nbems.common.core.utils.IdUtils;
import com.ake.system.config.MinIOConfig;
import com.ake.system.domain.SysFile;
import com.ake.system.mapper.SysFileMapper;
import com.ake.system.service.ISysFileService;
import com.ake.system.utils.MinIOClientUtils;
import com.ake.system.utils.PicUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.ObjectStat;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author yzt
 * @description
 * @date 2021/12/8 16:04
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {
    private static final Logger log = LoggerFactory.getLogger(SysFileServiceImpl.class);
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private MinIOConfig minIOConfig;
    @Autowired
    private MinIOClientUtils minIOClientUtils;

    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public R<SysFile> upload(MultipartFile file) throws Exception {
        SysFile sysFile = new SysFile();
        String originalFileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ByteArrayOutputStream osMini = new ByteArrayOutputStream();
        String contentType= file.getContentType();
        String prefix = DateUtils.datePath()+"/";
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uid = IdUtils.simpleUUID();
        String storeName = uid+suffix;
        String miniStoreName = uid+ PicUtils.MINI_SUFFIX+suffix;
        Long size = file.getSize();
        String bucketName = minIOConfig.getBucketName();

        boolean flag = true;
        if(PicUtils.isPic(suffix)) {
            double scale = 1f;
            //计算缩放比例，为尺寸比例的开方
            log.info("scale is {}", scale);
            Thumbnails.of(in).scale(scale).toOutputStream(os);
            size = (long) os.toByteArray().length;
            Thumbnails.of(new ByteArrayInputStream(os.toByteArray())).size(PicUtils.MINI_WIDTH, PicUtils.MINI_HEIGHT).toOutputStream(osMini);
            sysFile.setMiniName(miniStoreName);
            flag = minIOClientUtils.putObject(bucketName, prefix+storeName, new ByteArrayInputStream(os.toByteArray()), contentType);
            flag = minIOClientUtils.putObject(bucketName, prefix+miniStoreName, new ByteArrayInputStream(osMini.toByteArray()), contentType);
        } else {
            flag = minIOClientUtils.putObject(bucketName, prefix+storeName, in, contentType);
        }
        if (!flag) {
            return R.fail("上传失败");
        }
        sysFile.setBucketName(MinioConstants.BUCKET_NBEMS);
        sysFile.setContentType(contentType);
        sysFile.setPrefix(prefix);
        sysFile.setOriginalName(originalFileName);
        sysFile.setStoreName(storeName);
        sysFile.setFileSize(size);
        sysFile.setStoreName(storeName);
        sysFile.setSuffix(suffix);
        //计算文件大小
        BigDecimal sizeKB = new BigDecimal(sysFile.getFileSize());
        sizeKB = sizeKB.divide(new BigDecimal(1024L), 2, BigDecimal.ROUND_HALF_EVEN);
        int compare = sizeKB.compareTo(new BigDecimal(1024));
        if(compare < 0) {
            sysFile.setFileSizeName(sizeKB +"KB");
        } else {
            sysFile.setFileSizeName(sizeKB.divide(new BigDecimal(1024L), 2, BigDecimal.ROUND_HALF_EVEN) +"MB");
        }
        String username = SecurityUtils.getUsername();
        sysFile.setCreateTime(new Date());
        sysFile.setCreateBy(username);
        sysFile.setDownloads(0);
        long id = sysFileMapper.insert(sysFile);
        if(sysFile.getId()==null) {
            log.info("file id is {}", id);
            sysFile.setId(id);
        }
        return R.ok(sysFile);
    }

    @Override
    public void downloadById(HttpServletResponse response, long id) throws Exception {
        SysFile file = sysFileMapper.selectById(id);
        Integer downloads = file.getDownloads();
        // 下载次数+1
        file.setDownloads(downloads + 1);
        sysFileMapper.updateById(file);
        downloadByStoreName(response, file.getPrefix(), file.getStoreName(), file.getOriginalName());
    }


    public void downloadByStoreName(HttpServletResponse response, String prefix, String storeName, String originalName) throws Exception {
        String bucketName = minIOConfig.getBucketName();
        InputStream is = null;
        ObjectStat stat = minIOClientUtils.statObject(bucketName, prefix + storeName);
        response.setContentType(stat.contentType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(originalName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"");
        is = minIOClientUtils.getObject(bucketName, prefix+storeName);
        IOUtils.copy(is, response.getOutputStream());
    }

}
