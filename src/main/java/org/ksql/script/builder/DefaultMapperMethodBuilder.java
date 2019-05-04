package org.ksql.script.builder;

import org.ksql.script.annotation.Delete;
import org.ksql.script.annotation.Insert;
import org.ksql.script.annotation.Select;
import org.ksql.script.annotation.Update;
import org.ksql.script.bo.MapperMethod;
import org.ksql.script.bo.SqlType;
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
        MapperMethod mapperMethod = new MapperMethod();
        initBaseSql(mapperMethod, methodAgent);
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

    private void initBaseSql(MapperMethod mapperMethod, MethodAgent methodAgent) {
        if (methodAgent.hasAnnotation(Select.class)) {
            mapperMethod.setBaseSql(methodAgent.getAnnotation(Select.class).value());
            mapperMethod.setSqlType(SqlType.SELECT);
        } else if (methodAgent.hasAnnotation(Update.class)) {
            mapperMethod.setBaseSql(methodAgent.getAnnotation(Update.class).value());
            mapperMethod.setSqlType(SqlType.SELECT);
        } else if (methodAgent.hasAnnotation(Insert.class)) {
            mapperMethod.setBaseSql(methodAgent.getAnnotation(Insert.class).value());
            mapperMethod.setSqlType(SqlType.SELECT);
        } else if (methodAgent.hasAnnotation(Delete.class)) {
            mapperMethod.setBaseSql(methodAgent.getAnnotation(Delete.class).value());
            mapperMethod.setSqlType(SqlType.SELECT);
        }
    }
}
