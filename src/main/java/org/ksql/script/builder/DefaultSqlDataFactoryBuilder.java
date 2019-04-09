package org.ksql.script.builder;


import org.ksql.script.fatory.DefaultSqlDataFactory;
import org.ksql.script.fatory.SqlDataFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultSqlDataFactoryBuilder {
    private static final SqlDataFactory factoryCache = new DefaultSqlDataFactory();
    private static AtomicInteger init = new AtomicInteger(0);

    public static SqlDataFactory create(String... packAddr) {

        if (init.compareAndSet(1, 0)) {
        }

        return factoryCache;
    }
}
