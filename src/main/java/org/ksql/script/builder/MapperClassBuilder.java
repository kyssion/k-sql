package org.ksql.script.builder;

import org.ksql.script.bo.MapperClass;

public interface MapperClassBuilder {
    MapperClass build(Class<?> mapperclass);
}
