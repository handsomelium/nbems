package com.ake.nbems.common.core.utils.bean;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zww Email：zhongweiwei@gz.iscas.ac.cn
 * @version 1.0,
 * @desc TODO
 * @date 2020/6/10 20:22
 * Copyright (C) 2018-2019 GZ-ISCAS Inc., All Rights Reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
public class CopyBeanUtil {

    private static String[] ignoreProp = {};

    private static final Logger logger = LoggerFactory.getLogger(CopyBeanUtil.class);

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }  else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            return ((Collection)object).isEmpty();
        } else {
            return object instanceof Map ? ((Map)object).isEmpty() : false;
        }
    }
    /**
     * 将source中与target中类型和名称相同的属性值赋值给对应的entity的属性，并返回target
     *
     * @param source          源对象
     * @param target          目标对象
     * @param ignoreNullField 是否忽略空值
     */
    public static void copyProperties(Object source, Object target, Boolean ignoreNullField) {
        List<Map<String, Object>> sourceFields = getFieldInfo(source);
        if (isEmpty(sourceFields)) {
            return;
        }
        for (Map sourceFieldMap : sourceFields) {
            try {
                Field field = target.getClass().getDeclaredField(sourceFieldMap.get("name").toString());
                // 源对象属性值为空 或属性类型不一致 则返回继续下一条
                if (ignoreNullField && isEmpty(sourceFieldMap.get("value")) ||
                        !sourceFieldMap.get("type").equals(field.getType().toString())) {
                    continue;
                }
                field.setAccessible(true);
                field.set(target, sourceFieldMap.get("value"));
            } catch (Exception ex) {
                // 查看其父类属性
                try {
                    Field superField = target.getClass().getSuperclass()
                            .getDeclaredField(sourceFieldMap.get("name").toString());
                    superField.setAccessible(true);
                    superField.set(target, sourceFieldMap.get("value"));
                } catch (Exception e1) {
                    logger.debug("目标字段反射失败："+e1.getMessage());
                }
            }
        }
    }

    /**
     * @param source
     * @param clazz
     * @return
     */
    public static <T> List<T> copyList(List<?> source, Class<T> clazz) {
        if (source == null || source.size() == 0) {
            return new ArrayList<T>();
        }
        List<T> res = new ArrayList<T>(source.size());
        for (Object o : source) {
            try {
                T t = clazz.newInstance();
                res.add(t);
                BeanUtils.copyProperties(o, t);
            } catch (Exception e) {
                logger.error("copyList error", e);
            }
        }
        return res;
    }

    public static <T> T copy(Object source, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e){
            logger.error("copyBean error", e);
        }
        return t;
    }

    /**
     * @param source
     * @param clazz
     * @param ignoreProperties 忽略属性值
     * @throws
     * @return T
     */
    public static <T> T copy(Object source, Class<T> clazz, String... ignoreProperties) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t, ignoreProperties);
        } catch (Exception e){
            logger.error("copyBean error", e);
        }
        return t;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            logger.debug(o.getClass().getSimpleName()+"字段："+fieldName+"取值失败 "+e.getMessage());
            return null;
        }
    }

    /**
     * 获取属性名数组
     */
    private static String[] getFieldName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private static List<Map<String, Object>> getFieldInfo(Object o) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (isEmpty(o)) {
            return null;
        }
        List<Field> fields = new ArrayList<Field>(Arrays.asList(o.getClass().getDeclaredFields()));
        //如果存在父类，获取父类的属性值，类型，名称并添加到一起
        Class sc = o.getClass().getSuperclass();
        if (sc != null) {
            fields.addAll(Arrays.asList(sc.getDeclaredFields()));
        }
        for (Field field : fields) {
            Map<String, Object> infoMap = new HashMap<String, Object>();
            infoMap.put("type", field.getType().toString());
            infoMap.put("name", field.getName());
            infoMap.put("value", getFieldValueByName(field.getName(), o));
            list.add(infoMap);
        }
        return list;
    }
}
