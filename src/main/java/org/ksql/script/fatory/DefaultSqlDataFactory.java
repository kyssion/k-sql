package org.ksql.script.fatory;


import org.ksql.script.bo.SqlData;
import org.ksql.script.bo.SqlDataMap;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultSqlDataFactory implements SqlDataFactory{

    private static ConcurrentHashMap<Class<?>, SqlDataMap> cache =
            new ConcurrentHashMap<>();

    public <T> T getSqlData(Class<T> mapper) {
        return (T) cache.get(mapper).getMapperProxy();
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
