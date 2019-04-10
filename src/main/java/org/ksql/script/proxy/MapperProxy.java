package org.ksql.script.proxy;

import org.ksql.script.bo.SqlData;
import org.ksql.script.bo.SqlDataMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

    private T object;

    private Class<?> objectType;

    private SqlDataMap sqlDataMap;

    public MapperProxy(T item,SqlDataMap sqlDataMap) {
        this.object = item;
        this.objectType = item.getClass();
        this.sqlDataMap = sqlDataMap;
    }

    @Override
    public SqlData invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return sqlDataMap.getMethodMap().get(method.getName());
    }

    public T getObject() {
        return object;
    }

    public Class<?> getObjectType() {
        return objectType;
    }
}
