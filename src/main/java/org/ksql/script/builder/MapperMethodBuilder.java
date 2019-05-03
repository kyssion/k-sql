package org.ksql.script.builder;

import org.ksql.script.bo.MapperMethod;
import org.mirror.reflection.agent.MethodAgent;

public interface MapperMethodBuilder {
    MapperMethod builder(MethodAgent methodAgent);
}
