package com.ake.system.mapper;

import com.ake.system.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yezt
 * @description
 * @date 2021/12/24 16:16
 */
public interface StudentMapper extends MongoRepository<Student, Long> {
}
