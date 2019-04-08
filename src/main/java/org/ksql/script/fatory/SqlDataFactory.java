package org.ksql.script.fatory;



public interface SqlDataFactory {
    <T> T getSqlData(Class<T> mapper);
}

