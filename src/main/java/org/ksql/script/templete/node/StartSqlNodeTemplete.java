package org.ksql.script.templete.node;

import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示一个sql序列化的开始
 */
public class StartSqlNodeTemplete implements SqlNodeTemplete {

    private static final StringBuffer start = new StringBuffer();

    private static final SqlNodeType type = SqlNodeType.NULL_NODE;

    private SqlNodeTemplete next;

    @Override
    public StringBuffer toSqlString(MirrorObject value) {
        return new StringBuffer();
    }

    @Override
    public void setNext(SqlNodeTemplete next) {
        this.next = next;
    }

    @Override
    public List<Object> toSqlParams(MirrorObject value) throws ErrorException {
        return null;
    }

    @Override
    public SqlNodeTemplete next() {
        return this.next;
    }

    @Override
    public SqlNodeType getNodeType() {
        return type;
    }

    public ResultsCollective createResultsCollective(Object param) {
        SqlNodeTemplete item = this.next;
        StringBuffer sql = new StringBuffer();
        List<Object> list = new ArrayList<>();
        MirrorObject mirrorObject = MirrorObject.forObject(param);
        while (item != null) {
            sql.append(item.toSqlString(mirrorObject));
            List<Object> params = item.toSqlParams(mirrorObject);
            if (params != null) {
                list.addAll(params);
            }
            item=item.next();
        }
        ResultsCollective resultsCollective = new ResultsCollective();
        resultsCollective.setSql(sql.toString());
        resultsCollective.setParam(list);
        return resultsCollective;
    }
}
