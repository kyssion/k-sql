package org.ksql.script.fatory;

import org.ksql.script.mapper.MapperClass;

public interface MapperClassFactory {
    MapperClass create(Class<?> mapper);
    MapperClass getMapper(Class<?> mapper);
}
