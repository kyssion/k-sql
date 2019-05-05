package org.ksql;

import org.ksql.script.bo.MapperClass;
import org.ksql.script.bo.MapperMethod;
import org.ksql.script.builder.MapperClassFactoryBuilder;
import org.ksql.script.fatory.MapperClassFactory;
import org.ksql.script.templete.SqlTemplete;

import java.util.Map;

public class SqlCreate {
    public static void main(String[] args) {
        MapperClassFactory factory = MapperClassFactoryBuilder.build("");
        MapperClass mapperClass = factory.create(TestMapper.class);
        Map<String, MapperMethod> mapperMethodMap=mapperClass.getMethodMap();
        MapperMethod method = mapperMethodMap.get("test");
        SqlTemplete templete = method.getSqlTemplete();

    }
}
