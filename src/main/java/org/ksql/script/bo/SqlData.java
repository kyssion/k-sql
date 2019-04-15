package org.ksql.script.bo;

import org.ksql.script.conversion.TypeConversion;

import java.util.Map;

/**
 * 一个mapper文件中一个method对应的参数表达
 */
public class SqlData {

    private String sql;
    private Map<String,Param> sqlParams;
    private String baseSql;
    private Object baseParams;
    private Class<?> baseParamsType;
    private ReturnParam returnParam;
    private TypeConversion tsTypeConversion;

    public String getBaseSql() {
        return baseSql;
    }

    public void setBaseSql(String baseSql) {
        this.baseSql = baseSql;
    }

    public Object getBaseParams() {
        return baseParams;
    }

    public void setBaseParams(Object baseParams) {
        this.baseParams = baseParams;
    }

    public Class<?> getBaseParamsType() {
        return baseParamsType;
    }

    public void setBaseParamsType(Class<?> baseParamsType) {
        this.baseParamsType = baseParamsType;
    }

    public Map<String, Param> getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(Map<String, Param> sqlParams) {
        this.sqlParams = sqlParams;
    }

    public ReturnParam getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(ReturnParam returnParam) {
        this.returnParam = returnParam;
    }

    public TypeConversion getTsTypeConversion() {
        return tsTypeConversion;
    }

    public void setTsTypeConversion(TypeConversion tsTypeConversion) {
        this.tsTypeConversion = tsTypeConversion;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
