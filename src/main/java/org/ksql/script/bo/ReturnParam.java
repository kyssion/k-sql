package org.ksql.script.bo;

/**
 * 针对返回值进行封装,提供返回值类类型和转化的一层封装
 */
public class ReturnParam {
    private Class<?> returnParamType;
    private Object result;
    private Object baseData;
    private Class<?> baseDataType;

    public ReturnParam(Object result, Class<?> returnParamType, Class<?> baseDataType) {
        this.result = result;
        this.returnParamType = returnParamType;
        this.baseDataType = baseDataType;
    }

    public void setBaseData(Object baseData) {
        this.baseData = baseData;
    }

    public Class<?> getReturnParamType() {
        return returnParamType;
    }

    public Object getResult() {
        return result;
    }

    public Object getBaseData() {
        return baseData;
    }

    public Class<?> getBaseDataType() {
        return baseDataType;
    }
}