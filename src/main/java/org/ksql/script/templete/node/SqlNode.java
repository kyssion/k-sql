package org.ksql.script.templete.node;

import org.mirror.reflection.mirror.MirrorObject;

import java.util.Collections;
import java.util.List;

public interface SqlNode {

    StringBuilder sqlCreate(MirrorObject object, List<Object> params);

    default List<Object> objectToList(Object itemList){
        List<Object> newItemList = null;
        if (itemList.getClass() == List.class) {
            newItemList = (List<Object>) itemList;
        } else if (itemList.getClass() == int[].class) {
            newItemList = Collections.singletonList(itemList);
        } else if (itemList.getClass() == boolean[].class) {
            newItemList = Collections.singletonList(itemList);
        } else if (itemList.getClass() == double[].class) {
            newItemList = Collections.singletonList(itemList);
        } else if (itemList.getClass() == float[].class) {
            newItemList = Collections.singletonList(itemList);
        } else if (itemList.getClass() == char[].class) {
            newItemList = Collections.singletonList(itemList);
        } else if (itemList.getClass() == Object[].class) {
            newItemList = Collections.singletonList(itemList);
        } else {

        }
        return newItemList;
    }
}
