package org.ksql.script.templete.engine;


import org.ksql.script.annotation.Param;
import org.ksql.script.exception.SqlTempleteIsNullException;
import org.ksql.script.exception.TypeErrorException;
import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlTemplete;
import org.ksql.script.templete.node.SqlNode;
import org.mirror.reflection.agent.MethodAgent;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class DefaultSqlTempeteEngine implements SqlTempleteEngine {

    public SqlTemplete createByMethod(String baseSql, MethodAgent methodAgent) {
        Object paramObject = null;
        if (methodAgent.getMethodParamCount() == 0) {
            try {
                paramObject = methodAgent.getParams()[0].getType()
                        .getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Map<String, Object> param = new HashMap<>();
            Parameter[] parameters = methodAgent.getParams();
            for (int a = 0; a < parameters.length; a++) {
                Parameter par = parameters[a];
                Param parInfo = par.getAnnotation(Param.class);
                if (parInfo != null) {
                    String name = parInfo.name();
                    param.put(name, parameters[a].getType());
                }
            }
            paramObject = param;
        }
        return this.createByObject(baseSql, paramObject);
    }

    public SqlTemplete createByObject(String baseSql, Object paramItem) {

    }

    @Override
    public SqlTemplete create(String baseSql, Object paramObject) {
        if (!(paramObject instanceof MethodAgent)) {
            throw new TypeErrorException(this.getClass().getName() + ": the create_method need a" +
                    "param which type is MethodAgent");
        }
        return this.createByMethod(baseSql, (MethodAgent) paramObject);
    }


    public class DefaultSqlTempete implements SqlTemplete {

        private SqlNode sqlNode;

        public void setSqlNode(SqlNode sqlNode) {
            this.sqlNode = sqlNode;
        }

        @Override
        public ResultsCollective createSql(Object params) throws SqlTempleteIsNullException{
            if (this.sqlNode == null) {
                throw new SqlTempleteIsNullException(this.getClass());
            }
            return null;
        }
    }
}
