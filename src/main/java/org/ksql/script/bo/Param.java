package org.ksql.script.bo;

import java.util.List;

public class Param {
    private List paramItem;
    private Class<?> paramType;
    private String paramName;
    private boolean isArr;

    public boolean isArr() {
        return isArr;
    }

    public void setArr(boolean arr) {
        isArr = arr;
    }

    public List getParamItem() {
        return paramItem;
    }

    public void setParamItem(List paramItem) {
        this.paramItem = paramItem;
    }

    public Class<?> getParamType() {
        return paramType;
    }

    public void setParamType(Class<?> paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
