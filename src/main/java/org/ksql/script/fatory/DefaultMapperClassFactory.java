package org.ksql.script.fatory;

import org.ksql.script.mapper.MapperClass;
import org.ksql.script.builder.DefaultMapperClassBuilder;
import org.ksql.script.builder.MapperClassBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMapperClassFactory implements MapperClassFactory {

    private static final Map<Class, MapperClass> cache = new ConcurrentHashMap<>();
    private static final MapperClassBuilder builder = new DefaultMapperClassBuilder();

    @Override
    public MapperClass create(Class<?> mapper) {
        if (cache.containsKey(mapper)) {
            return cache.get(mapper);
        }
        MapperClass mapperClass = builder.build(mapper);
        cache.put(mapper, mapperClass);
        return mapperClass;
    }
}
