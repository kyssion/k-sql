package org.ksql.script.builder;


import org.ksql.script.bo.SqlData;

public interface SqlDataBuilder {
    SqlData createData(Class<?> item);
}
