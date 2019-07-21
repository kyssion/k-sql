package org.ksql.script.templete;

public enum SqlNodeType {
    Base_Node("Base Node"),Object_Node("Object Node"),
    List_Object_Node("List Object Node"),Null_Node("null node"),Template_Node("template node");
    String desc;
    SqlNodeType(String desc){
        this.desc=desc;
    }
}
