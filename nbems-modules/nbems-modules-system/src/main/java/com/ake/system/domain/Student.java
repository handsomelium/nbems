package com.ake.system.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author yezt
 * @description
 * @date 2021/12/24 15:54
 */
@Document(collection = "test_student")
@Data
public class Student implements Serializable {
    @Id
    private Long id;
    private String name;
    private Map<Integer, String> course;
    private Integer age;
    private Date createTime;
    private Boolean deleted;
}
