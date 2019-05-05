package org.ksql.script.templete.node;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;
import org.mirror.reflection.property.CollectionTypeParameterProcessor;

import java.util.*;

/**
 * 主要解析 :xxx这种类型的字符串
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
        if (keyArrIndex == this.keyArr.length) {
            return new StringBuffer("?");
        }
        Class<?> valueType = value.getGetterType(keyArr[keyArrIndex]);
        Object valueObject = value.getValue(keyArr[keyArrIndex]);
        if (valueObject instanceof List || valueType.isArray()) {
            int length = CollectionTypeParameterProcessor.collectionSize(valueObject);
            StringBuffer buffer = new StringBuffer();
            for (int a = 0; a < length; a++) {
                Object itemObject = CollectionTypeParameterProcessor.getValueFromArr(a, valueObject);
                if (a == 0) {
                    buffer.append(toSqlString(MirrorObject.forObject(itemObject), keyArrIndex + 1));
                } else {
                    buffer.append(",").append(toSqlString(MirrorObject.forObject(itemObject), keyArrIndex + 1));
                }
            }
            return buffer;
        } else {
            return toSqlString(MirrorObject.forObject(valueObject), ++keyArrIndex);
        }
    }

    @Override
    public StringBuffer toSqlString(MirrorObject value) {
        return toSqlString(value, 0);
    }

    @Override
    public List<Object> toSqlParams(MirrorObject value) throws ErrorException {
        List<Object> list = new ArrayList<>();
        toSqlParams(value, 0, list, value.getValue(keyArr[0]));
        return list;
    }

    private void toSqlParams(MirrorObject value, int index, List<Object> list, Object item) {
        if (index == keyArr.length) {
            list.add(item);
        }
        Class<?> valueType = value.getGetterType(keyArr[index]);
        Object valueObject = value.getValue(keyArr[index]);
        if (valueObject instanceof List || valueType.isArray()) {
            int length = CollectionTypeParameterProcessor.collectionSize(valueObject);
            for (int a = 0; a < length; a++) {
                Object itemObject = CollectionTypeParameterProcessor.getValueFromArr(a,valueObject);
                toSqlParams(MirrorObject.forObject(itemObject),index+1,list,itemObject);
            }
        } else {
            toSqlParams(MirrorObject.forObject(valueObject),index+1,list,valueObject);
        }
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
