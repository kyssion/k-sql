package org.ksql.script.bo;


import org.ksql.script.templete.SqlTemplete;

import java.util.List;
import java.util.Map;

public class MapperMethod {
    private SqlType sqlType;
    private String baseSql;

    private String sql;
    private Map<String, Param> sqlParams;

    private SqlTemplete sqlTemplete;

    private List<Object> baseParams;
    private List<Class<?>> baseParamsType;
    private Class<?> retObjectType;

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

    public void setBaseParams(List<Object> baseParams) {
        this.baseParams = baseParams;
    }

    public List<Class<?>> getBaseParamsType() {
        return baseParamsType;
    }

    public void setBaseParamsType(List<Class<?>> baseParamsType) {
        this.baseParamsType = baseParamsType;
    }

    public Class<?> getRetObjectType() {
        return retObjectType;
    }

    public void setRetObjectType(Class<?> retObjectType) {
        this.retObjectType = retObjectType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Param> getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(Map<String, Param> sqlParams) {
        this.sqlParams = sqlParams;
    }

    public String getBaseSql() {
        return baseSql;
    }

    public void setBaseSql(String baseSql) {
        this.baseSql = baseSql;
    }

    public Object getBaseParams() {
        return baseParams;
    }
    public class Param {
        private List paramItem;
        private Class<?> paramType;
        private String paramName;
        private boolean isArr;

        public boolean isArr() {
            return isArr;
        }

        public void setArr(boolean arr) {
            isArr = arr;
        }

        public List getParamItem() {
            return paramItem;
        }

        public void setParamItem(List paramItem) {
            this.paramItem = paramItem;
        }

        public Class<?> getParamType() {
            return paramType;
        }

        public void setParamType(Class<?> paramType) {
            this.paramType = paramType;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
    }
}
