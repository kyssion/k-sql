package org.ksql.script.mapper;

import org.ksql.script.templete.ResultsCollective;

import java.util.HashMap;
import java.util.Map;

/**
 * Internal counterpart of a mapper class
 */
public class MapperClass {
    private Class<?> mapperClass;
    private String mapperId;
    private final Map<String, MapperMethod> methodMap;

    public MapperClass() {
        this.methodMap = new HashMap<>();
    }

    public MapperMethod getMapperMethod(String methodId) {
        MapperMethod method = methodMap.get(methodId);
        return method;
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

    public ResultsCollective getMapperMathodResults(String mapperMethodId, Object... param) throws Exception {
        MapperMethod mapperMethod = this.methodMap.get(mapperMethodId);
        if (param == null) {
            param = new Object[]{};
        }
        if (param.length == 1) {
            return mapperMethod.getSqlTemplete().createSql(param[0]);
        } else {
            Map<String, Object> item = mapperMethod.paramsListToMap(param);
            return mapperMethod.getSqlTemplete().createSql(item);
        }
    }
}
