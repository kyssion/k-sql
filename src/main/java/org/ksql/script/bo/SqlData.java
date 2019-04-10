package org.ksql.script.bo;

import org.ksql.script.conversion.TypeConversion;

import java.util.Map;

/**
 * 一个mapper文件中一个method对应的参数表达
 */
public class SqlData<T,S> {

    private String sql;
    private Map<String,String> sqlParams;
    private Map<String,Class<?>> paramsType;
    private ReturnParam<T,S> returnParam;
    private TypeConversion<S> tsTypeConversion;

    public Map<String, String> getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(Map<String, String> sqlParams) {
        this.sqlParams = sqlParams;
    }

    public ReturnParam<T, S> getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(ReturnParam<T, S> returnParam) {
        this.returnParam = returnParam;
    }

    public TypeConversion<S> getTsTypeConversion() {
        return tsTypeConversion;
    }

    public void setTsTypeConversion(TypeConversion<S> tsTypeConversion) {
        this.tsTypeConversion = tsTypeConversion;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Class<?>> getParamsType() {
        return paramsType;
    }

    public void setParamsType(Map<String, Class<?>> paramsType) {
        this.paramsType = paramsType;
    }


}
