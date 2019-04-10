package org.ksql.script.builder;

import org.ksql.script.bo.SqlData;

import java.lang.reflect.Method;

public class DefaultSqlDataBuilder implements SqlDataBuilder{

    @Override
    public SqlData builder(Method method) {
        SqlData sqlData = new SqlData();
        return sqlData;
    }
}
