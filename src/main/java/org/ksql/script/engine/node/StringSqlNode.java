package org.ksql.script.engine.node;

import java.util.List;

public class StringSqlNode implements SqlNode {

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
    public List<Object> toSqlParams() {
        return null;
    }

    @Override
    public SqlNode next() {
        return this.nextSqlNode;
    }
}
