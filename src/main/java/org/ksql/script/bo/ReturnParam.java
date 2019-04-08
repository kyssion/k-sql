package org.ksql.script.bo;


import org.ksql.script.conversion.TypeConversion;

public class ReturnParam<T,S> {
    private Class<T> returnParamType;
    private T result;
    private TypeConversion<T,S> tsTypeConversion;

    public TypeConversion<T, S> getTsTypeConversion() {
        return tsTypeConversion;
    }

    public void setTsTypeConversion(TypeConversion<T, S> tsTypeConversion) {
        this.tsTypeConversion = tsTypeConversion;
    }

    public Class<T> getReturnParamType() {
        return returnParamType;
    }

    public void setReturnParamType(Class<T> returnParamType) {
        this.returnParamType = returnParamType;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T conversion(S s){
        return this.tsTypeConversion.concersion(s);
    }
}
