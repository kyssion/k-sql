package org.ksql.script.builder;

import org.ksql.script.annotation.Mapper;
import org.ksql.script.fatory.DefaultMapperClassFactory;
import org.ksql.script.fatory.MapperClassFactory;
import org.mirror.reflection.io.ClassUtill;

import java.util.Set;

public class MapperClassFactoryBuilder {

    public static  MapperClassFactory build(String packageName) {
        Set<Class<?>> classSet = ClassUtill.getClassSet(packageName);
        MapperClassFactory mapperClassFactory = new DefaultMapperClassFactory();
        for (Class<?> mapperClass : classSet) {
            if(mapperClass.isInterface()&&mapperClass.getAnnotation(Mapper.class)!=null) {
                mapperClassFactory.create(mapperClass);
            }
        }
        return mapperClassFactory;
    }
}
