package org.ksql.script.templete;

import org.ksql.script.templete.node.SqlNode;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.ArrayList;
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

    public static ResultsCollective createResultsCollective(List<SqlNode> sqlNodes, MirrorObject object) {
        ResultsCollective resultsCollective = new ResultsCollective();
        List<Object> list = new ArrayList<>();
        resultsCollective.setParam(list);
        StringBuilder st = new StringBuilder();
        for (SqlNode sqlNode : sqlNodes) {
            st.append(sqlNode.sqlCreate(object, list));
        }
        resultsCollective.setSql(st.toString());
        return resultsCollective;
    }
}
