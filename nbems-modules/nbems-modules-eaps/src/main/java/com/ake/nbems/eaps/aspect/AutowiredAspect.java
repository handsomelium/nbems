package com.ake.nbems.eaps.aspect;

import com.ake.nbems.eaps.annotation.Autowiredd;
import com.ake.nbems.eaps.controller.TestController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class AutowiredAspect {

    @Bean
    private void Autowiredd(){
        TestController testController = new TestController();

        Class<? extends TestController> clazz = testController.getClass();
        // 获取所有的属性值
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            Autowiredd annotation = field.getAnnotation(Autowiredd.class);
            if (annotation != null){
                field.setAccessible(true);
                // 获取属性的类型
                Class<?> type = field.getType();
                Object o;
                try {
                    o = type.newInstance();
                    field.set(testController, o);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
        System.out.println(testController.getTestService());

    }
}
