package org.ksql.script.builder;

import org.ksql.script.annotation.Delete;
import org.ksql.script.annotation.Insert;
import org.ksql.script.annotation.Select;
import org.ksql.script.annotation.Update;
import org.ksql.script.mapper.MapperMethod;
import org.ksql.script.exception.NoSqlInfoError;
import org.ksql.script.mapper.SqlType;
import org.ksql.script.templete.SqlTemplete;
import org.ksql.script.templete.engine.DefaultSqlTempeteEngine;
import org.ksql.script.templete.engine.SqlTempleteEngine;
import org.mirror.reflection.agent.MethodAgent;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * The class method and the constructor class handled in sql to construct the classMapperMethod
 */
public class DefaultMapperMethodBuilder implements MapperMethodBuilder {

    private SqlTempleteEngine sqlTempleteEngine
            = new DefaultSqlTempeteEngine();

    @Override
    public MapperMethod builder(MethodAgent methodAgent) {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setMethodAgent(methodAgent);
        mapperMethod.setRetObjectType(methodAgent.getReturnType());
        mapperMethod.setParamsType(methodAgent.getParamType());
        initMapperInfo(mapperMethod, methodAgent);
        initSqlTemplete(mapperMethod, methodAgent);
        return mapperMethod;
    }

    private void initMapperInfo(MapperMethod mapperMethod, MethodAgent methodAgent) {
        Annotation[] anns = methodAgent.getAllAnnotation();
        for (Annotation ann : anns) {
            if (ann.annotationType() == Select.class) {
                mapperMethod.setSqlType(SqlType.SELECT);
                Select select = methodAgent.getAnnotation(Select.class);
                mapperMethod.setBaseSql(select.value());
            } else if (ann.annotationType() == Update.class) {
                mapperMethod.setSqlType(SqlType.UPDETE);
                Update select = methodAgent.getAnnotation(Update.class);
                mapperMethod.setBaseSql(select.value());
            } else if (ann.annotationType() == Delete.class) {
                mapperMethod.setSqlType(SqlType.DELETE);
                Delete select = methodAgent.getAnnotation(Delete.class);
                mapperMethod.setBaseSql(select.value());
            } else if (ann.annotationType() == Insert.class) {
                mapperMethod.setSqlType(SqlType.INSERT);
                Insert select = methodAgent.getAnnotation(Insert.class);
                mapperMethod.setBaseSql(select.value());
            }
        }
    }

    private void initSqlTemplete(MapperMethod mapperMethod, MethodAgent methodAgent) {
        String baseSql = mapperMethod.getBaseSql();
        if (baseSql == null || "".equals(baseSql)) {
            throw new NoSqlInfoError(methodAgent);
        }
        mapperMethod.setSqlTemplete(this.sqlTempleteEngine.create(baseSql));
    }
}
