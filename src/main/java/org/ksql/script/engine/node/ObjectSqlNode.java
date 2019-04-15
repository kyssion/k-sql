package org.ksql.script.engine.node;

import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectSqlNode implements SqlNode{

    private Object value;
    private MirrorObject mirrorObject;
    //附近相同的类型进行合并
    private List<String> keyString;

    private SqlNode nextSqlNode;

    public ObjectSqlNode(List<String> keyString){
        this.keyString=keyString;
    }

    public void setValue(Object value) {
        this.value = value;
        this.mirrorObject=MirrorObject.forObject(value);
    }

    public void setNextSqlNode(SqlNode nextSqlNode) {
        this.nextSqlNode = nextSqlNode;
    }

    @Override
    public StringBuffer toSqlString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int a=0;a<keyString.size();a++){
            if(a==0){
                stringBuffer.append("?");
            }else{
                stringBuffer.append(",?");
            }
        }
        return stringBuffer;
    }

    @Override
    public List<Object> toSqlParams() throws ErrorException {
        if(this.mirrorObject==null){
            throw new ErrorException();
        }

        List<Object> params = new ArrayList<>();
        for (String key:this.keyString){
            params.add(this.mirrorObject.getValue(key));
        }

        return params;
    }

    @Override
    public SqlNode next() {
        return this.nextSqlNode;
    }
}
