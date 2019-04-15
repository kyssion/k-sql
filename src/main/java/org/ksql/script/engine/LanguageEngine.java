package org.ksql.script.engine;

import org.mirror.reflection.agent.MethodAgent;

public interface LanguageEngine {
    EngineParams create(String sql, MethodAgent methodAgent , Object...params);
}
