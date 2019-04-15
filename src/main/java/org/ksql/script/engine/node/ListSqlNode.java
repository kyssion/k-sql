package org.ksql.script.engine.node;

import java.util.ArrayList;
import java.util.List;

/**
 * :L{}
 */
public class ListSqlNode implements SqlNode {

    private String keyString;
    private List<String> keyItemList;
    private List<Object> list;
    private SqlNode nextSqlNode;
    private StringBuffer compileString;

    public ListSqlNode(String keyString) {
        this.keyString = keyString;
        this.keyItemList = new ArrayList<>();
        init();
    }

    public void setNextSqlNode(SqlNode nextSqlNode) {
        this.nextSqlNode = nextSqlNode;
    }

    private void init(){
        StringBuffer stringBuffer = new StringBuffer();
        int start=0;
        int end = 0;
        while(end<this.keyString.length()){
            if(this.keyString.charAt(end)==':'){
                stringBuffer.append(this.keyString.substring(start,end));
                int next = this.keyString.indexOf(',',end);
                if(next<this.keyString.length()) {
                    end = next;
                    stringBuffer.append("?,");
                }else{
                    end = this.keyString.indexOf(")");
                    stringBuffer.append("?");
                }
                keyItemList.add(this.keyString.substring(start+1,end));
                end++;
                start=end-1;
            }
        }
        stringBuffer.append(this.keyString.substring(start,end));
        this.compileString=stringBuffer;
    }

    @Override
    public StringBuffer toSqlString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int a=0;a<this.list.size();a++){
            if(a==0){
                stringBuffer.append(this.compileString);
            }else{
                stringBuffer.append(",").append(stringBuffer);
            }
        }
        return stringBuffer;
    }

    @Override
    public List<Object> toSqlParams() {
        return new ArrayList<>();
    }

    @Override
    public SqlNode next() {
        return this.nextSqlNode;
    }
}
