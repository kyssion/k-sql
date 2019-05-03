package org.ksql.script.proxy;

import org.ksql.script.annotation.Mapper;
import org.ksql.script.bo.MapperClass;
import org.ksql.script.bo.MapperMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

    private T object;

    private Class<?> objectType;

    private MapperClass mapperClass;

    public MapperProxy(T item, MapperClass mapperClass) {
        this.object = item;
        this.objectType = item.getClass();
        this.mapperClass = mapperClass;
    }

    @Override
    public MapperMethod invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return mapperClass.getMethodMap().get(method.getName());
    }

    public T getObject() {
        return object;
    }

    public Class<?> getObjectType() {
        return objectType;
    }
}
