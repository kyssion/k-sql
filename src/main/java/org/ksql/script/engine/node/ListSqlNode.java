package org.ksql.script.engine.node;

import io.netty.handler.codec.serialization.ObjectEncoder;
import org.ksql.script.engine.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.ArrayList;
import java.util.List;

/**
 * :L{(:xxx,:ppp)}
 */
public class ListSqlNode implements SqlNode {

    private static final SqlNodeType type = SqlNodeType.LIST_VALUE_NODE;

    private String keyString;
    private List<String> keyItemList;
    private int length;
    private SqlNode nextSqlNode;
    private String listName;
    private StringBuffer compileString;

    public ListSqlNode(String keyString) throws ErrorException {
        this.keyString = keyString;
        this.keyItemList = new ArrayList<>();
        init();
    }

    public void setNextSqlNode(SqlNode nextSqlNode) {
        this.nextSqlNode = nextSqlNode;
    }

    private void init() throws ErrorException {
        boolean hasToken = false;
        if (this.keyString.startsWith("(") || this.keyString.endsWith(")")) {
            this.keyString = this.keyString.substring(1, this.keyString.length() - 1);
            hasToken = true;
        }
        this.keyString = this.keyString.replace(" ", "");
        String[] arr = this.keyString.split(",");
        for (int a = 0; a < arr.length; a++) {
            String key = arr[a].substring(1, arr[a].length() - 1);
            int lastIndex = key.lastIndexOf('.');
            String name = key.substring(0, lastIndex);
            if (name.equals(this.listName)) {
                throw new ErrorException();
            }
            this.listName = name;
            this.keyItemList.add(key);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int a = 0; a < arr.length; a++) {
            if (a == 0) {
                stringBuffer.append("?");
            } else {
                stringBuffer.append(",?");
            }
        }
        if (hasToken) {
            stringBuffer = new StringBuffer("(").append(stringBuffer).append(")");
        }
        this.compileString = stringBuffer;
    }

    @Override
    public StringBuffer toSqlString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int a = 0; a < this.length; a++) {
            if (a == 0) {
                stringBuffer.append(this.compileString);
            } else {
                stringBuffer.append(",").append(stringBuffer);
            }
        }
        return stringBuffer;
    }

    @Override
    public List<Object> toSqlParams(Object value) {
        MirrorObject mirrorObject = MirrorObject.forObject(value);
        List list = mirrorObject.getValue(this.listName, List.class);
        return new ArrayList<>();
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
