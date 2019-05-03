package org.ksql.script.engine;

public enum SqlNodeType {
    SQLSTR_NODE("simple string"),LIST_VALUE_NODE("list item"),
    OBJECT_VALUE_NODE("object item"),NULL_NODE("other");
    String desc;
    SqlNodeType(String desc){
        this.desc=desc;
    }
}
