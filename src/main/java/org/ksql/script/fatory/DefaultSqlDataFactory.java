package org.ksql.script.fatory;


import java.util.concurrent.ConcurrentHashMap;

public class DefaultSqlDataFactory implements SqlDataFactory{

    private static ConcurrentHashMap<Class<?>,Object> cache =
            new ConcurrentHashMap<>();

    public <T> T getSqlData(Class<T> mapper) {
        return null;
    }
}
