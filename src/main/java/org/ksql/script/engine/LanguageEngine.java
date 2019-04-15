package org.ksql.script.engine;

import org.ksql.script.bo.Param;

import java.lang.reflect.Method;
import java.util.Map;

public interface LanguageEngine {
    String createSql(String baseSql,Map<String,Param> params);
    Map<String, Param> createSqlParam(Method method);
}
