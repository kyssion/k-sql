package org.ksql.script.fatory;


import org.ksql.script.bo.SqlData;

public interface SqlDataFactory {
    SqlData getSqlData(Class<?> mapper);
}

