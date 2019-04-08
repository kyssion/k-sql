package org.ksql.script.bo;

import java.util.Map;

/**
 *
 */
public class SqlData {

    private String sql;
    private Map<String,String> params;
    private Map<String,Class<?>> paramsType;
    private ReturnParam returnParam;
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, Class<?>> getParamsType() {
        return paramsType;
    }

    public void setParamsType(Map<String, Class<?>> paramsType) {
        this.paramsType = paramsType;
    }
}
