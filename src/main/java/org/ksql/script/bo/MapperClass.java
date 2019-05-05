package org.ksql.script.bo;

import java.util.Map;

public class MapperClass {
    private Class<?> mapperClass;
    private String mapperId;
    private final Map<String, MapperMethod> methodMap;
    private Object mapperProxy;

    public MapperClass(Map<String, MapperMethod> methodMap) {
        this.methodMap = methodMap;
    }

    public MapperMethod getMapperMethod(String methodId) {
        MapperMethod method = methodMap.get(methodId);
        return method;
    }

    public Object getMapperProxy() {
        return mapperProxy;
    }

    public void setMapperProxy(Object mapperProxy) {
        this.mapperProxy = mapperProxy;
    }

    public Class<?> getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(Class<?> mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public Map<String, MapperMethod> getMethodMap() {
        return methodMap;
    }

}
