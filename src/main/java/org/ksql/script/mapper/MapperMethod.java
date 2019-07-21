package org.ksql.script.mapper;


import org.ksql.script.annotation.*;
import org.ksql.script.templete.SqlTemplete;
import org.mirror.reflection.agent.MethodAgent;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mapper class 中每一个sql的单独封装
 */
public class MapperMethod {
    private SqlType sqlType;
    private String baseSql;
    private List<Class<?>> paramsType;
    private MethodAgent methodAgent;
    private SqlTemplete sqlTemplete;
    private Class<?> retObjectType;//返回值类型

    public MethodAgent getMethodAgent() {
        return methodAgent;
    }

    public void setMethodAgent(MethodAgent methodAgent) {
        this.methodAgent = methodAgent;
    }

    public SqlTemplete getSqlTemplete() {
        return sqlTemplete;
    }

    public void setSqlTemplete(SqlTemplete sqlTemplete) {
        this.sqlTemplete = sqlTemplete;
    }

    public SqlType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
    }

    public String getBaseSql() {
        return baseSql;
    }

    public void setBaseSql(String baseSql) {
        this.baseSql = baseSql;
    }

    public List<Class<?>> getParamsType() {
        return paramsType;
    }

    public void setParamsType(List<Class<?>> paramsType) {
        this.paramsType = paramsType;
    }

    public Class<?> getRetObjectType() {
        return retObjectType;
    }

    public void setRetObjectType(Class<?> retObjectType) {
        this.retObjectType = retObjectType;
    }

    public Map<String, Object> paramsListToMap(Object[] params) {
        Map<String, Object> param = new HashMap<>();
        Parameter[] parameters = methodAgent.getParams();
        if (params.length != parameters.length) {
            return param;
        }
        for (int a = 0; a < parameters.length; a++) {
            Parameter par = parameters[a];
            Param parInfo = par.getAnnotation(Param.class);
            if (parInfo != null) {
                String name = parInfo.value();
                param.put(name, params[a]);
            }
        }
        return param;
    }
}
