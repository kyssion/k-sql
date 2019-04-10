package org.ksql.script.builder;

import org.ksql.script.bo.SqlData;

import java.lang.reflect.Method;

public interface SqlDataBuilder {
    SqlData builder(Method method);
}
