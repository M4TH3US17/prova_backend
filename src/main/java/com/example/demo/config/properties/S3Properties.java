package com.example.demo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "s3")
public class S3Properties {

    private String accessKeyId;
    private String secretKeyId;
    private String region;
    private String bucket;
}
