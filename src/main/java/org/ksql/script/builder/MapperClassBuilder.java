package org.ksql.script.builder;

import org.ksql.script.mapper.MapperClass;

public interface MapperClassBuilder {
    MapperClass build(Class<?> mapperclass);
}
