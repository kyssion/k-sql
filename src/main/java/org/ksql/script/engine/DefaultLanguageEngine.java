package org.ksql.script.engine;

import org.ksql.script.annotation.Mapper;
import org.mirror.reflection.agent.MethodAgent;

public class DefaultLanguageEngine implements LanguageEngine {

    private static LanguageEngine languageEngine = new DefaultLanguageEngine();

    public static EngineParams createEngineParams(MethodAgent methodAgent) {
        Mapper mapper = methodAgent.getAnnotation(Mapper.class);
        if (mapper == null || mapper.id().equals("")) {
            return null;
        }

        String baseSql = mapper.id();

        return languageEngine.create(baseSql, methodAgent);
    }

    @Override
    public EngineParams create(String sql, MethodAgent methodAgent) {

        return null;
    }
}
