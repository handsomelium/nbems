package com.ake.common.mongodb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * @author yezt
 * @description
 * @date 2021/12/24 10:44
 */
@Component
public class MongodbUtils {
    public static MongodbUtils mongodbUtils;

    @PostConstruct
    public void init() {
        mongodbUtils = this;
        mongodbUtils.mongoTemplate = this.mongoTemplate;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存数据对象，集合为数据对象中@Document 注解所配置的collection
     * @param obj 数据对象
     */
    public static void save(Object obj) {
        mongodbUtils.mongoTemplate.save(obj);
    }

    /**
     * 指定集合保存数据对象
     * @param obj 数据对象
     * @param collectionName 集合名
     */
    public static void save(Object obj, String collectionName) {

        mongodbUtils.mongoTemplate.save(obj, collectionName);
    }

    /**
     * 根据数据对象中的id删除数据，集合为数据对象中@Document 注解所配置的collection
     * @param obj 数据对象
     */
    public static void remove(Object obj) {

        mongodbUtils.mongoTemplate.remove(obj);
    }

    /**
     * 指定集合 根据数据对象中的id删除数据
     * @param obj 数据对象
     * @param collectionName 集合名
     */
    public static void remove(Object obj, String collectionName) {

        mongodbUtils.mongoTemplate.remove(obj, collectionName);
    }

    /**
     * 根据key，value到指定集合删除数据
     * @param key 键
     * @param value 值
     * @param collectionName 集合名
     */
    public static void removeById(String key, Object value, String collectionName) {

        Criteria criteria = Criteria.where(key).is(value);
        criteria.and(key).is(value);
        Query query = Query.query(criteria);
        mongodbUtils.mongoTemplate.remove(query, collectionName);
    }

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     * @param accordingKey 修改条件 key
     * @param accordingValue 修改条件 value
     * @param collectionName 集合名
     */
    public static void updateFirst(String accordingKey, Object accordingValue, Map<String, Object> params, String collectionName) {
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        mongodbUtils.mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     * @param accordingKey 修改条件 key
     * @param accordingValue 修改条件 value
     * @param clazz 类对象
     */
    public static void updateFirst(String accordingKey, Object accordingValue, Map<String, Object> params, Class<?> clazz) {
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        mongodbUtils.mongoTemplate.updateFirst(query, update, getCollectionName(clazz));
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     * @param accordingKey 修改条件 key
     * @param accordingValue 修改条件 value
     * @param collectionName 集合名
     */
    public static void updateMulti(String accordingKey, Object accordingValue, Map<String, Object> params,
                                   String collectionName) {
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        mongodbUtils.mongoTemplate.updateMulti(query, update, collectionName);
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     * @param accordingKey 修改条件 key
     * @param accordingValue 修改条件 value
     * @param clazz 集合名
     */
    public static void updateMulti(String accordingKey, Object accordingValue, Map<String, Object> params,
                                   Class<?> clazz) {
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        mongodbUtils.mongoTemplate.updateMulti(query, update, getCollectionName(clazz));
    }

    /**
     * 根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     * @param obj 数据对象
     * @param findKeys 查询条件 key
     * @param findValues 查询条件 value
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues) {
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongodbUtils.mongoTemplate.find(query, obj.getClass());
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集
     * @param obj 数据对象
     * @param findKeys 查询条件 key
     * @param findValues 查询条件 value
     * @param collectionName 集合名
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongodbUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集 并排倒序
     * @param obj 数据对象
     * @param findKeys 查询条件 key
     * @param findValues 查询条件 value
     * @param collectionName 集合名
     * @param sort 排序字段
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName ,String sort) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        query.with(Sort.by(Sort.Direction.DESC, sort));
        List<? extends Object> resultList = mongodbUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    public static List<?> find(Class<?> obj, Map<String, Object> params) {
        Criteria criteria = null;
        int i = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (i == 0) {
                criteria = Criteria.where(entry.getKey()).is(entry.getValue());
            } else {
                criteria.and(entry.getKey()).is(entry.getValue());
            }
            i++;
        }
        Query query = Query.query(criteria);
        return mongodbUtils.mongoTemplate.find(query, obj, getCollectionName(obj));
    }

    /**
     * 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param obj 数据对象
     * @param findKeys 查询条件 key
     * @param findValues 查询条件 value
     * @return
     */
    public static Object findOne(Object obj, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongodbUtils.mongoTemplate.findOne(query, obj.getClass());
        return resultObj;
    }

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     * @param obj 数据对象
     * @param findKeys 查询条件 key
     * @param findValues 查询条件 value
     * @param collectionName 集合名
     * @return
     */
    public static Object findOne(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongodbUtils.mongoTemplate.findOne(query, obj.getClass(), collectionName);
        return resultObj;
    }

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     * @param obj 数据对象
     * @return
     */
    public static List<? extends Object> findAll(Object obj) {

        List<? extends Object> resultList = mongodbUtils.mongoTemplate.findAll(obj.getClass());
        return resultList;
    }

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>  List<T> findAll(Class<T> clazz){
        List<T> resultList = mongodbUtils.mongoTemplate.findAll(clazz);
        return resultList;
    }

    /**
     * 指定集合 查询出所有结果集
     * @param obj 数据对象
     * @param collectionName 集合名
     * @return
     */
    public static List<? extends Object> findAll(Object obj, String collectionName) {

        List<? extends Object> resultList = mongodbUtils.mongoTemplate.findAll(obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 指定集合 查询出所有结果集
     * @param clazz
     * @param collectionName
     * @param <T>
     * @return
     */
    public static <T> List<T> findAll(Class<T> clazz, String collectionName) {
        List<T> resultList = mongodbUtils.mongoTemplate.findAll(clazz, collectionName);
        return resultList;
    }

    /**
     * 获取注解值
     * @param clazz
     * @throws
     * @return java.lang.String
     */
    public static String getCollectionName(Class<?> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        if (document != null) {
            return document.collection();
        }
        return null;
    }
}
