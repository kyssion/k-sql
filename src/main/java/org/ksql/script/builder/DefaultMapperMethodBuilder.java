package org.ksql.script.builder;

import org.ksql.script.bo.MapperMethod;
import org.ksql.script.exception.NoSqlInfoError;
import org.ksql.script.templete.SqlTemplete;
import org.ksql.script.templete.engine.DefaultSqlTempeteEngine;
import org.ksql.script.templete.engine.SqlTempleteEngine;
import org.mirror.reflection.agent.MethodAgent;


public class DefaultMapperMethodBuilder implements MapperMethodBuilder {

    private SqlTempleteEngine sqlTempleteEngine
            = new DefaultSqlTempeteEngine();

    @Override
    public MapperMethod builder(MethodAgent methodAgent) {
        MapperMethod mapperMethod = new MapperMethod(methodAgent);
        initSqlTemplete(mapperMethod, methodAgent);
        return mapperMethod;
    }

    private void initSqlTemplete(MapperMethod mapperMethod, MethodAgent methodAgent) {
        String baseSql = mapperMethod.getBaseSql();
        if (baseSql == null || "".equals(baseSql)) {
            throw new NoSqlInfoError(methodAgent);
        }
        SqlTemplete sqlTemplete = this.sqlTempleteEngine.create(baseSql, methodAgent);
        if (sqlTemplete != null) {
            mapperMethod.setSqlTemplete(sqlTemplete);
        }
    }
}
