package org.ksql.script.templete.node;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.*;

/**
 * 主要解析 :xxx,:xxx这种类型的字符串
 */
public class ObjectSqlNodeTempletem implements SqlNodeTemplete {
    private static final SqlNodeType type = SqlNodeType.OBJECT_TEMP_NODE;

    private String[] keyArr = null;
    private String key = null;
    private SqlNodeTemplete next;

    public ObjectSqlNodeTempletem(String baseSql) {
        baseSql = baseSql.replaceAll(" ", "").toLowerCase();
        key = baseSql;
        keyArr = baseSql.substring(1).split(".");
    }

    public SqlNodeTemplete getNext() {
        return next;
    }

    public String getKey() {
        return key;
    }

    @Override
    public void setNext(SqlNodeTemplete next) {
        this.next = next;
    }

    private StringBuffer toSqlString(MirrorObject value, int keyArrIndex) {

    }

    @Override
    public StringBuffer toSqlString(MirrorObject value) {
        return toSqlString(value,0);
    }

    @Override
    public List<Object> toSqlParams(MirrorObject value) throws ErrorException {
        return value.getValue(this.keyArr);
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
