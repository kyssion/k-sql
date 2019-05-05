package org.ksql;

import org.ksql.script.annotation.Mapper;
import org.ksql.script.annotation.Param;
import org.ksql.script.annotation.Select;

@Mapper
public interface TestMapper {
    @Select("select * from user where name=:name and age=:age")
    int test(@Param("name") String name, @Param("age") String age);
}
