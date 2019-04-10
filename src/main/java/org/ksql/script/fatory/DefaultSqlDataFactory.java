package org.ksql.script.fatory;


import org.ksql.script.annotation.Mapper;
import org.ksql.script.bo.SqlData;
import org.ksql.script.bo.SqlDataMap;
import org.mirror.reflection.mirror.MirrorClass;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSqlDataFactory implements SqlDataFactory{

    private static ConcurrentHashMap<Class<?>, SqlDataMap> cache =
            new ConcurrentHashMap<>();

    public <T> T getSqlData(Class<T> mapper) {
        if(cache.contains(mapper)){
           return (T) cache.get(mapper).getMapperProxy();
        }
        SqlDataMap sqlDataMap = initSqlData(mapper);
        return (T) sqlDataMap.getMapperProxy();
    }

    private <T> SqlDataMap initSqlData(Class<T> mapper) {
        //初始化map
        SqlDataMap sqlDataMap = new SqlDataMap();
        sqlDataMap.setMapper(mapper);

        MirrorClass mapperClass = MirrorClass.forClass(mapper);
        sqlDataMap.setMapperId(mapperClass.getType().getName());

        return sqlDataMap;
    }

    @Override
    public SqlData getSqlData(Class<?> mapper, String methodName) {
        return cache.get(mapper).getMethodMap().get(methodName);
    }

    @Override
    public SqlDataMap getSqlDataMap(Class<?> mapper) {
        return cache.get(mapper);
    }
}
