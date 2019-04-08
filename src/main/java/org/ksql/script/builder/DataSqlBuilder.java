package org.ksql.script.builder;


import org.ksql.script.bo.SqlData;

public interface DataSqlBuilder {
    SqlData createData(Class<?> item);
}
