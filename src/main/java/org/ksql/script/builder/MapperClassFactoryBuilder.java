package org.ksql.script.builder;

import org.ksql.script.fatory.MapperClassFactory;

import java.util.concurrent.atomic.AtomicReference;

public class MapperClassFactoryBuilder {

    public static AtomicReference<MapperClassFactory> mapperClassFactoryAtomicReference =
            new AtomicReference<>();

    public static MapperClassFactory build(String packName) {
        return mapperClassFactoryAtomicReference.get();
    }
}
