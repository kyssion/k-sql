package org.ksql.script.fatory;


import org.ksql.script.bo.SqlData;
import org.ksql.script.builder.DataSqlBuilder;
import org.ksql.script.builder.DefaultDataSqlBuilder;

public class DefaultSqlDataFactory implements SqlDataFactory{

    private static final DataSqlBuilder dataBuilder = new DefaultDataSqlBuilder();

    @Override
    public SqlData getSqlData(Class<?> mapper) {
        return dataBuilder.createData(mapper);
    }
}
