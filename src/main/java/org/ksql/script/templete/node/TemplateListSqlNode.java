package org.ksql.script.templete.node;

import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public class TemplateListSqlNode implements SqlNode {

    private String before;
    private String end;
    private String mark;

    public TemplateListSqlNode(String mark) {
        this.before = "(";
        this.end = ")";
        this.mark = mark;
    }

    @Override
    public StringBuilder sqlCreate(MirrorObject mirrorObject, List<Object> params) {
        Object itemList = mirrorObject.getValue(mark);
        List<Object> newItemList = this.objectToList(itemList);

        StringBuilder st = new StringBuilder();
        boolean isAllStart = true;
        for (Object i : newItemList) {
            if (!isAllStart) {
                st.append(",");
            }
            boolean isStart = true;
            MirrorObject item = MirrorObject.forObject(i);
            String[] names = item.getGetterNames();
            if (names.length > 0) {
                st.append(this.before);
                for (String name : names) {
                    Object inItem = item.getValue(name);
                    if (inItem == null) {
                        continue;
                    }
                    if (isStart) {
                        st.append("?");
                        isStart = false;
                    } else {
                        st.append(",").append(item.getValue(name));
                    }
                    params.add(inItem);
                }
                st.append(this.end);
            }
            isAllStart = false;
        }
        return st;
    }
}
