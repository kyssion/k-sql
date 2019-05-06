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
        MapperMethod method =mapperClass.getMapperMethod("test2");
        SqlTemplete templete = method.getSqlTemplete();
        Map<String,Object> item = new HashMap<>();
        List<String> itel = new ArrayList<>();
        for (int a=0;a<10;a++){
            itel.add(""+a);
        }
        item.put("name",itel);
        item.put("age","222");
        Item item1 = new Item();
        item1.setAge("123");
        item1.setName("wang");
        ResultsCollective collective =templete.createSql(item1);
    }
}
