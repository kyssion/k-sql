package org.ksql.script.engine.node;

import org.ksql.script.engine.SqlNodeType;

import java.util.List;

public class StringSqlNode implements SqlNode {

    private static final SqlNodeType type = SqlNodeType.SQL_STR;

    private String node;

    private SqlNode nextSqlNode;

    public StringSqlNode(String node){
        this.node=node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @Override
    public StringBuffer toSqlString() {
        return new StringBuffer(this.node);
    }


    @Override
    public List<Object> toSqlParams(Object value) {
        return null;
    }

    @Override
    public SqlNode next() {
        return this.nextSqlNode;
    }

    @Override
    public SqlNodeType getNodeType() {
        return type;
    }
}
