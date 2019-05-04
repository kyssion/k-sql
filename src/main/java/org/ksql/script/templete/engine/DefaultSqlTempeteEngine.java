package org.ksql.script.templete.engine;


import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlTemplete;
import org.mirror.reflection.agent.MethodAgent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class DefaultSqlTempeteEngine implements SqlTempleteEngine {

    public SqlTemplete createByMethod(String baseSql, MethodAgent methodAgent) {
        Object paramObject = null;

        if (methodAgent.getMethodParamCount() == 0) {
            try {
                paramObject = methodAgent.getParams()[0].getType().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Map<String, Object> param = new HashMap<>();
            Parameter[] parameters = methodAgent.getParams();
            for (int a = 0; a < parameters.length; a++) {
                Parameter par = parameters[a];

            }
        }

        return this.create(baseSql, null);
    }

    @Override
    public SqlTemplete create(String baseSql, Object param) {
        return new DefaultSqlTempete();
    }


    public class DefaultSqlTempete implements SqlTemplete {
        @Override
        public ResultsCollective createSql(Object params) {
            return null;
        }
    }
}
