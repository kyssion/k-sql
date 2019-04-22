package org.ksql.script.builder;

import org.ksql.script.engine.EngineParams;

public interface EngineParamsBuilder {
    EngineParams create(String sql,Object param);
}
