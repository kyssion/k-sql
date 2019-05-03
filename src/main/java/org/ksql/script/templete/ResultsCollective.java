package org.ksql.script.templete;

import java.util.List;

public class ResultsCollective {
    private String sql;
    private List<Object> param;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParam() {
        return param;
    }

    public void setParam(List<Object> param) {
        this.param = param;
    }
}
