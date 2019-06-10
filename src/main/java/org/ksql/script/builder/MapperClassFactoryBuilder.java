package org.ksql.script.builder;

import org.ksql.script.fatory.DefaultMapperClassFactory;
import org.ksql.script.fatory.MapperClassFactory;
import org.mirror.reflection.io.ClassFindleUtil;
import org.mirror.reflection.io.test.IsA;

import java.util.Set;

public class MapperClassFactoryBuilder {

    private static MapperClassFactory factory = null;

    public static MapperClassFactory build(String packName) {
        if(factory==null){
            factory = addMappers(packName);
        }
        return  factory;
    }

    public static MapperClassFactory addMappers(String packageName, Class<?> superType) {
        ClassFindleUtil<Class<?>> resolverUtil = new ClassFindleUtil<>();
        resolverUtil.find(new IsA(superType), packageName);
        Set<Class<? extends Class<?>>> mapperSet = resolverUtil.getClasses();
        MapperClassFactory mapperClassFactory = new DefaultMapperClassFactory();
        for (Class<?> mapperClass : mapperSet) {
            if(mapperClass.isInterface()) {
                mapperClassFactory.create(mapperClass);
            }
        }
        return mapperClassFactory;
    }

    public static MapperClassFactory addMappers(String packageName) {
        return addMappers(packageName, Object.class);
    }

}
