package org.ksql.script.templete.node;

import org.ksql.script.exception.ErrorException;
import org.ksql.script.templete.SqlNodeType;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public class StringSqlNodeTemplete implements SqlNodeTemplete {

    private static final SqlNodeType type = SqlNodeType.STR_TEMP_NODE;

    private StringBuffer sqlNodeString;

    private SqlNodeTemplete next;

    public StringSqlNodeTemplete(String baseSqlNode){
        this.sqlNodeString = new StringBuffer(baseSqlNode);
    }

    public SqlNodeTemplete getNext() {
        return next;
    }

    public void setNext(SqlNodeTemplete next) {
        this.next = next;
    }


    @Override
    public StringBuffer toSqlString(MirrorObject value) {
        return this.sqlNodeString;
    }

    @Override
    public List<Object> toSqlParams(MirrorObject value) throws ErrorException {
        return null;
    }

    @Override
    public SqlNodeTemplete next() {
        return this.getNext();
    }

    @Override
    public SqlNodeType getNodeType() {
        return type;
    }
}
