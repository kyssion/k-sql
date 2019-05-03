package org.ksql.script.engine;

import org.ksql.script.engine.node.SqlNode;
import org.ksql.script.engine.node.StartSqlNode;


public class SqlTemplete {
    private SqlNode start=new StartSqlNode();
    private Object value;

    public SqlNode getStart() {
        return start;
    }

    public void setStart(SqlNode start) {
        this.start = start;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }



}
