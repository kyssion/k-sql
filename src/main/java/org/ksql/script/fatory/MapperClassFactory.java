package org.ksql.script.fatory;

import org.ksql.script.bo.MapperClass;

public interface MapperClassFactory {
    MapperClass create(Class<?> mapper);
}
