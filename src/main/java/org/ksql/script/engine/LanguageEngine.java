package org.ksql.script.engine;

import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.agent.MethodAgent;

public interface LanguageEngine {
    EngineParams create(String sql, MethodAgent methodAgent) throws ErrorException;
}
