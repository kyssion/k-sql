package org.ksql.script;


import org.ksql.script.bo.SqlData;
import org.ksql.script.fatory.DefaultSqlDataFactory;
import org.ksql.script.fatory.SqlDataFactory;

public class SqlDataFactoryBuilder {
    private static final SqlDataFactory sqlDataFactory =
            new DefaultSqlDataFactory();

    public static SqlData createAndGetBuild(Class<?> mapper){
        return sqlDataFactory.getSqlData(mapper);
    }
}
