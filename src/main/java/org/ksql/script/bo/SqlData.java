package org.ksql.script.bo;

import org.ksql.script.conversion.TypeConversion;

import java.util.Map;

/**
 * 一个mapper文件中一个method对应的参数表达
 */
public class SqlData {

    private String sql;
    private Map<String,Param> sqlParams;
    private ReturnParam returnParam;
    private TypeConversion tsTypeConversion;

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
