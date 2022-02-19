package com.ake.system.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yzt
 * @description
 * @date 2021/12/8 15:56
 */
@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinIOConfig {
    /**
     * 连接url
     */
    private String endpoint;
    /**
     * 端口
     */
    private int port;
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 所在桶
     */
    private String bucketName;

    @Bean
    public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
        return new MinioClient(endpoint, port, accessKey, secretKey);
    }
}
