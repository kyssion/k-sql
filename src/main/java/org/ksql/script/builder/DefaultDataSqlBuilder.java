package org.ksql.script.builder;


import org.ksql.script.bo.SqlData;
import org.mirror.reflection.DefaultReflectorFactory;
import org.mirror.reflection.mirror.MirrorClass;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultDataSqlBuilder implements DataSqlBuilder{

    private static ConcurrentHashMap<Class<?>, SqlData> cache =
            new ConcurrentHashMap<>();

    @Override
    public SqlData createData(Class<?> item) {
        if(cache.contains(item)){
            return cache.get(item);
        }

        MirrorClass mirrorClass = MirrorClass.forClass(item,new DefaultReflectorFactory());

        //1. 解析sql
        //2. 创建数据映射
        //3. 创建返回值更新类

        return null;
    }
}
