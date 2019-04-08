package org.ksql.script.fatory;


import org.ksql.script.bo.SqlData;
import org.ksql.script.bo.SqlDataMap;

public interface SqlDataFactory {
    <T> T getSqlData(Class<T> mapper);
    SqlData getSqlData(Class<?> mapper, String methodName);
    SqlDataMap getSqlDataMap(Class<?> mapper);
}

