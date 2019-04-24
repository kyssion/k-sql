package org.ksql.script.engine;

import org.ksql.script.annotation.Mapper;
import org.ksql.script.annotation.Param;
import org.ksql.script.builder.DefaultEngineParamsBuilder;
import org.ksql.script.builder.EngineParamsBuilder;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.agent.MethodAgent;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class DefaultLanguageEngine implements LanguageEngine {

    private static LanguageEngine languageEngine = new DefaultLanguageEngine();
    private static EngineParamsBuilder engineParamsBuilder = new DefaultEngineParamsBuilder();

    public static EngineParams createEngineParams(MethodAgent methodAgent) {
        Mapper mapper = methodAgent.getAnnotation(Mapper.class);
        if (mapper == null || mapper.id().equals("")) {
            return null;
        }

        String baseSql = mapper.id();

        try {
            return languageEngine.create(baseSql, methodAgent);
        } catch (ErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EngineParams create(String sql, MethodAgent methodAgent) throws ErrorException {
        Object param = null;
        if (methodAgent.getMethod().getParameterCount() > 1) {
            Map<String, Object> map = new HashMap<>();
            Parameter[] parameters = methodAgent.getMethod().getParameters();
            for (Parameter parameter : parameters) {
                Class<?> paramClass = parameter.getType();
                Param paramAnnotation = parameter.getAnnotation(Param.class);
                if (paramAnnotation == null || paramAnnotation.name() == null) {
                    throw new ErrorException();
                } else {
                    try {
                        map.put(paramAnnotation.name(), paramClass.getConstructor().newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            param = map;
        } else {
            try {
                param = methodAgent.getParamType()[0].getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return engineParamsBuilder.create(sql, param);
    }
}
