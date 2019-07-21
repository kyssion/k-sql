package org.ksql.script.builder;

import org.ksql.script.annotation.Mapper;
import org.ksql.script.mapper.MapperClass;
import org.ksql.script.mapper.MapperMethod;
import org.ksql.script.exception.NotInterfaceError;
import org.mirror.reflection.agent.MethodAgent;
import org.mirror.reflection.mirror.MirrorClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultMapperClassBuilder implements MapperClassBuilder {

    private MapperMethodBuilder methodBuilder = new DefaultMapperMethodBuilder();

    @Override
    public MapperClass build(Class<?> mapperclass) {
        MapperClass mapperClass = new MapperClass(new HashMap<>());
        MirrorClass mirrorClass = MirrorClass.forClass(mapperclass);

        if (!mirrorClass.isInferface()){
            throw new NotInterfaceError(mirrorClass);
        }
        //init mapper type
        mapperClass.setMapperClass(mapperclass);

        //init mapperMethodMap
        List<MethodAgent> methodList = mirrorClass.getAllMethod();
        Map<String, MapperMethod> mapperMethodMap = mapperClass.getMethodMap();
        for(MethodAgent methodAgent:methodList){
            MapperMethod mapperMethod = methodBuilder.builder(methodAgent);
            mapperMethodMap.put(methodAgent.getMethodName(),mapperMethod);
        }

        //init mapperMethodId
        Mapper classmap = mirrorClass.getAnnotation(Mapper.class);
        if(classmap!=null&&!"".equals(classmap.id())){
            mapperClass.setMapperId(classmap.id());
        }else{
            mapperClass.setMapperId(mirrorClass.getClassName());
        }

        //init MapperProxy
        mapperClass.setMapperProxy(new Object());
        return mapperClass;
    }
}
