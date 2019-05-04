package org.ksql.script.templete;

public enum SqlNodeType {
    STR_TEMP_NODE("simple string"),LIST_TEMP_NODE("list item"),
    OBJECT_TEMP_NODE("object item"),NULL_NODE("other"),START_NODE("start node");
    String desc;
    SqlNodeType(String desc){
        this.desc=desc;
    }
}
