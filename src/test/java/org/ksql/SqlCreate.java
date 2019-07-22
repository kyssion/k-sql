package org.ksql;

import org.ksql.mapper.TestMapper;
import org.ksql.script.mapper.MapperClass;
import org.ksql.script.mapper.MapperMethod;
import org.ksql.script.builder.MapperClassFactoryBuilder;
import org.ksql.script.fatory.MapperClassFactory;
import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlTemplete;

public class SqlCreate {
    public static void main(String[] args) throws Exception {
        new SqlCreate().test();
//        new SqlCreate().test1();
    }

    public void test1() throws Exception {
        MapperClassFactory factory = MapperClassFactoryBuilder.build("org.ksql.mapper");
        Item item1 = new Item();
        item1.setAge("123");
        item1.setName("wang");
        ResultsCollective resultsCollective =
                factory.create(TestMapper.class)
                        .getMapperMathodResults("test2",item1);
    }

    public void test() throws Exception {
        MapperClassFactory factory = MapperClassFactoryBuilder.build("");
        MapperClass mapperClass = factory.create(TestMapper.class);
        MapperMethod method = mapperClass.getMapperMethod("test2");
        SqlTemplete templete = method.getSqlTemplete();
        Item item1 = new Item();
        item1.setAge("123");
        item1.setName("wang");
        Item item2 = new Item();
        item2.setName("wang2");
        item2.setAge("123333");
        ResultsCollective collective = templete.createSql(item1);
        ResultsCollective collective1 = templete.createSql(item2);
    }
}
