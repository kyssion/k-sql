package org.ksql.script.builder;

import org.ksql.script.engine.EngineParams;

public class DefaultEngineParamsBuilder implements EngineParamsBuilder {
    @Override
    public EngineParams create(String sql, Object param) {
        return null;
    }
}
