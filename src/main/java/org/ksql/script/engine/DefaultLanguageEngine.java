package org.ksql.script.engine;

import org.ksql.script.annotation.Mapper;
import org.ksql.script.bo.Param;
import org.mirror.reflection.agent.MethodAgent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultLanguageEngine implements LanguageEngine {

    private static LanguageEngine languageEngine = new DefaultLanguageEngine();

    public static String createSql(MethodAgent methodAgent,Map<String,Param> paramMap){
        Annotation mapper = methodAgent.getAnnotation(Mapper.class);
        if(mapper==null){
            return null;
        }
        return languageEngine.createSql("",paramMap);
    }

    public static Map<String,Param> createSqlParam(MethodAgent methodAgent){
        return languageEngine.createSqlParam(methodAgent.getMethod());
    }

    @Override
    public String createSql(String baseSql, Map<String, Param> params) {
        return null;
    }

    @Override
    public Map<String, Param> createSqlParam(Method method) {

        return null;
    }
}
