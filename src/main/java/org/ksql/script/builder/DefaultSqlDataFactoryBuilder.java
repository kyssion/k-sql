package org.ksql.script.builder;


import org.ksql.script.fatory.DefaultSqlDataFactory;
import org.ksql.script.fatory.SqlDataFactory;

public class DefaultSqlDataFactoryBuilder {
    private static final SqlDataFactory sqlDataFactory = new DefaultSqlDataFactory();

    public static SqlDataFactory build(String... packAddr) {

        return sqlDataFactory;
    }
}
