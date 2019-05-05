package org.ksql;

import org.ksql.script.bo.MapperClass;
import org.ksql.script.bo.MapperMethod;
import org.ksql.script.builder.MapperClassFactoryBuilder;
import org.ksql.script.fatory.MapperClassFactory;
import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlTemplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlCreate {
    public static void main(String[] args) throws Exception {
        MapperClassFactory factory = MapperClassFactoryBuilder.build("");
        MapperClass mapperClass = factory.create(TestMapper.class);
        Map<String, MapperMethod> mapperMethodMap=mapperClass.getMethodMap();
        MapperMethod method = mapperMethodMap.get("test");
        SqlTemplete templete = method.getSqlTemplete();
        Map<String,Object> item = new HashMap<>();
        List<String> itel = new ArrayList<>();
        for (int a=0;a<10;a++){
            itel.add(""+a);
        }
        item.put("name",itel);
        item.put("age","222");
        ResultsCollective collective =templete.createSql(item);
    }
}
