package org.ksql;

import org.ksql.script.builder.DefaultSqlDataFactoryBuilder;
import org.ksql.script.fatory.SqlDataFactory;

public class SqlBuildTest {
    public static void main(String[] args) {
        SqlDataFactory sqlDataFactory =
                DefaultSqlDataFactoryBuilder.build();
    }
}
