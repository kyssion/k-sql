package org.ksql.script.templete.node;

import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public class StringSqlNode implements SqlNode {

    private String nodeSql;

    public StringSqlNode(String nodeSql){
        this.nodeSql = nodeSql;
    }

    @Override
    public StringBuilder sqlCreate(MirrorObject object, List<Object> params) {
        return new StringBuilder(this.nodeSql);
    }
}
