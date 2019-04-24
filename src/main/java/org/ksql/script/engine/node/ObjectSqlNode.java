package org.ksql.script.engine.node;

import org.ksql.script.engine.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectSqlNode implements SqlNode {

    private static final SqlNodeType type = SqlNodeType.OBJECT_VALUE_NODE;

    private Object value;
    //附近相同的类型进行合并
    private List<String> keyString;

    private SqlNode nextSqlNode;

    public ObjectSqlNode(List<String> keyString) {
        this.keyString = keyString;
    }

    public void setNextSqlNode(SqlNode nextSqlNode) {
        this.nextSqlNode = nextSqlNode;
    }

    @Override
    public StringBuffer toSqlString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int a = 0; a < keyString.size(); a++) {
            if (a == 0) {
                stringBuffer.append("?");
            } else {
                stringBuffer.append(",?");
            }
        }
        return stringBuffer;
    }

    @Override
    public List<Object> toSqlParams(Object value) throws ErrorException {
        MirrorObject mirrorObject = MirrorObject.forObject(value);
        if (mirrorObject == null) {
            throw new ErrorException();
        }

        List<Object> params = new ArrayList<>();
        for (String key : this.keyString) {
            params.add(mirrorObject.getValue(key));
        }

        return params;
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
