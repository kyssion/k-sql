package org.ksql.script.templete.node;

import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public class ObjectSqlNode implements SqlNode {

    private String mark;

    public ObjectSqlNode(String mark) {
        this.mark = mark;
    }

    @Override
    public StringBuilder sqlCreate(MirrorObject mirrorObject, List<Object> params) {
        Object item = mirrorObject.getValue(this.mark);
        if (item != null) {
            params.add(item);
        }
        return new StringBuilder("?");
    }
}
