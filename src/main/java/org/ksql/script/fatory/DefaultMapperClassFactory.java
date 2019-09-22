package org.ksql.script.fatory;

import org.ksql.script.exception.ErrorException;
import org.ksql.script.mapper.MapperClass;
import org.ksql.script.builder.DefaultMapperClassBuilder;
import org.ksql.script.builder.MapperClassBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create a factory method for mapperclass
 */
public class DefaultMapperClassFactory implements MapperClassFactory {

    private static final Map<Class, MapperClass> cache = new ConcurrentHashMap<>();
    private static final MapperClassBuilder builder = new DefaultMapperClassBuilder();

    @Override
    public MapperClass create(Class<?> mapper) {
        MapperClass mapperClass = builder.build(mapper);
        cache.put(mapper, mapperClass);
        return mapperClass;
    }

    @Override
    public MapperClass getMapper(Class<?> mapper) {
        MapperClass mapperClass = cache.get(mapper);
        if(mapperClass!=null){
            return mapperClass;
        }else{
            throw new ErrorException("没有发现这个mapper信息："+mapper.getName());
        }
    }
}
