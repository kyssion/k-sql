package org.ksql.script.bo;

import java.util.Map;

public class SqlDataMap {

    private Class<?> mapper;
    private String mapperId;
    private Map<String,SqlData> methodMap;
    private Object mapperProxy;

    public Object getMapperProxy() {
        return mapperProxy;
    }

    public void setMapperProxy(Object mapperProxy) {
        this.mapperProxy = mapperProxy;
    }

    public Class<?> getMapper() {
        return mapper;
    }

    public void setMapper(Class<?> mapper) {
        this.mapper = mapper;
    }

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public Map<String, SqlData> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, SqlData> methodMap) {
        this.methodMap = methodMap;
    }
}
