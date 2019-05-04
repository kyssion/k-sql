package org.ksql.script.templete.node;

import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;

import java.util.List;

/**
 * 表示一个sql序列化的开始
 */
public class StartSqlNode implements SqlNode {

    private static final StringBuffer start = new StringBuffer();

    private static final SqlNodeType type = SqlNodeType.NULL_NODE;

    private SqlNode next;

    @Override
    public StringBuffer toSqlString(Object value) {
        return start;
    }

    public void setNext(SqlNode next) {
        this.next = next;
    }

    @Override
    public List<Object> toSqlParams(Object value) throws ErrorException {
        return null;
    }

    @Override
    public SqlNode next() {
        return next;
    }

    @Override
    public SqlNodeType getNodeType() {
        return type;
    }

    @Override
    public ResultsCollective getResultsCollective(Object param) {
        return null;
    }
}
