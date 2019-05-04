package org.ksql.script.templete.node;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;
import org.mirror.reflection.property.CollectionTypeParameterProcessor;

import java.util.*;

/**
 * 主要解析 :xxx,:xxx这种类型的字符串
 */
public class ObjectSqlNodeTempletem implements SqlNodeTemplete {
    private static final SqlNodeType type = SqlNodeType.OBJECT_TEMP_NODE;

    private String[] keyArr = null;

    private SqlNodeTemplete next;

    public ObjectSqlNodeTempletem(String baseSql) {
        baseSql = baseSql.replaceAll(" ", "").toLowerCase();
        keyArr = baseSql.split(",");
        for (String s : keyArr) {
            s = s.substring(1);
        }
    }

    public SqlNodeTemplete getNext() {
        return next;
    }

    public String[] getKeyArr() {
        return keyArr;
    }

    public void setKeyArr(String[] keyArr) {
        this.keyArr = keyArr;
    }

    @Override
    public void setNext(SqlNodeTemplete next) {
        this.next = next;
    }

    @Override
    public StringBuffer toSqlString(MirrorObject value) {
        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        int end = keyArr.length;
        while (index < end) {
            Class<?> paramType = value.getGetterType(keyArr[index]);
            if (paramType == List.class || paramType.isArray()) {
                int length = CollectionTypeParameterProcessor.collectionSize(value.getValue(keyArr[index]));
                for (int a = 0; a < length; a++) {
                    if (index == 0 && a == 0) {
                        stringBuffer.append("?");
                    } else {
                        stringBuffer.append(",?");
                    }
                }
            } else {
                if (index == 0) {
                    stringBuffer.append("?");
                } else {
                    stringBuffer.append(",?");
                }
            }
            index++;
        }
        return stringBuffer;
    }

    @Override
    public List<Object> toSqlParams(MirrorObject value) throws ErrorException {
        List<Object> list = new ArrayList<>();
        for (String key : keyArr) {
            Class<?> paramType = value.getGetterType(key);
            Object param = value.getValue(key);
            if (paramType == List.class || paramType.isArray()) {
                int length = CollectionTypeParameterProcessor.collectionSize(param);
                for (int a=0;a<length;a++){
                    list.add(CollectionTypeParameterProcessor.getValueFromArr(a,param));
                }
            }  else {
                list.add(value.getValue(key));
            }
        }
        return list;
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
