package org.ksql.script.bo;


import org.ksql.script.conversion.TypeConversion;

/**
 * 针对返回值进行封装,提供返回值类类型和转化的一层封装
 * @param <T> 返回值的类型
 * @param <S> 从数据库中查出的原始类型
 */
public class ReturnParam<T,S> {
    private Class<T> returnParamType;
    private T result;
    private S baseData;

    public Class<T> getReturnParamType() {
        return returnParamType;
    }

    public T getResult() {
        return result;
    }

    public S getBaseData() {
        return baseData;
    }
}
