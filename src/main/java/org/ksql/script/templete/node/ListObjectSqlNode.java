package org.ksql.script.templete.node;

import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public class ListObjectSqlNode implements SqlNode {

    private String mark;

    public ListObjectSqlNode(String mark) {
        this.mark = mark;
    }

    @Override
    public StringBuilder sqlCreate(MirrorObject mirrorObject, List<Object> params) {
        Object itemList = mirrorObject.getValue(mark);
        if (itemList == null) {

        }
        List<Object> newItemList = this.objectToList(itemList);
        StringBuilder st = new StringBuilder();
        boolean isStart = true;
        for (Object i : newItemList) {
            if (i == null) {
                continue;
            }
            if (isStart) {
                st.append("?");
                isStart = false;
            } else {
                st.append(",").append("?");
            }
            params.add(i);
        }
        return st;
    }

    private void addData(List<Object> from, List<Object> to) {
        to.addAll(from);
    }

}
