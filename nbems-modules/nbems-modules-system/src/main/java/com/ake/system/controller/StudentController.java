package com.ake.system.controller;

import com.ake.common.mongodb.utils.MongodbUtils;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.system.domain.Student;
import com.ake.system.mapper.StudentMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yezt
 * @description 测试
 * @date 2021/12/24 15:50
 */
@Api(tags = {"测试mongodb"})
@RestController
@RequestMapping("/test")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @ApiOperation(value = "新增")
    @GetMapping("/add")
    public AjaxResult testAdd() {
        Student student = new Student();
        student.setId(165464123123L);
        student.setAge(24);
        student.setCreateTime(new Date());
        student.setName("张三");
        Map<Integer, String> course = new HashMap<>();
        course.put(1, "语文");
        course.put(2, "数学");
        course.put(3, "英语");
        course.put(4, "体育");
        student.setCourse(course);
        studentMapper.save(student);
        return AjaxResult.success();
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/find")
    public AjaxResult testFind() {
        List<Student> all = studentMapper.findAll();
        return AjaxResult.success(all);
    }

    @ApiOperation(value = "条件查询")
    @GetMapping
    public AjaxResult testFindBy(Student student) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "张三");
        params.put("age", 24);
        List<?> all = MongodbUtils.find(Student.class, params);
//        List<Student> all = MongodbUtils.findAll(Student.class);
        return AjaxResult.success(all);
    }
}
