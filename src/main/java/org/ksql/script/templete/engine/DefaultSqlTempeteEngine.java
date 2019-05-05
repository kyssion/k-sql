package org.ksql.script.templete.engine;


import org.ksql.script.annotation.Param;
import org.ksql.script.exception.SqlTempleteIsNullException;
import org.ksql.script.exception.TypeErrorException;
import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlTemplete;
import org.ksql.script.templete.node.ObjectSqlNodeTempletem;
import org.ksql.script.templete.node.SqlNodeTemplete;
import org.ksql.script.templete.node.StartSqlNodeTemplete;
import org.ksql.script.templete.node.StringSqlNodeTemplete;
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
                    String name = parInfo.value();
                    param.put(name, parameters[a].getType());
                }
            }
            paramObject = param;
        }
        return this.createByObject(baseSql, paramObject);
    }

    public SqlTemplete createByObject(String baseSql, Object paramItem) {
        DefaultSqlTempete defaultSqlTempete = new DefaultSqlTempete();
        StartSqlNodeTemplete startSqlNodeTemplete = new StartSqlNodeTemplete();
        SqlNodeTemplete nodeTemplete = startSqlNodeTemplete;
        baseSql = baseSql.replaceAll("\n", "");
        char[] sqlChars = baseSql.toCharArray();
        int before = 0;
        for (int index = 0; index < sqlChars.length; index++) {
            if (sqlChars[index] == ':') {
                SqlNodeTemplete stringNodeTemp = new StringSqlNodeTemplete(new String(sqlChars, before, index - before));
                nodeTemplete.setNext(stringNodeTemp);
                nodeTemplete = stringNodeTemp;
                int findIndex = index+1;
                while (findIndex < sqlChars.length) {
                    if (!('a' <= sqlChars[findIndex] && 'z' >= sqlChars[findIndex])) {
                        break;
                    }
                    findIndex++;
                }
                SqlNodeTemplete objNodeTemp = new ObjectSqlNodeTempletem(new String(sqlChars, index, findIndex - index));
                nodeTemplete.setNext(objNodeTemp);
                nodeTemplete = objNodeTemp;
                before = findIndex;
            }
        }
        SqlNodeTemplete strNodeTemp = new StringSqlNodeTemplete(new String(sqlChars, before, sqlChars.length - before));
        nodeTemplete.setNext(strNodeTemp);
        defaultSqlTempete.setSqlNode(startSqlNodeTemplete);
        return defaultSqlTempete;
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
        private StartSqlNodeTemplete sqlNodeTemplete;

        public void setSqlNode(StartSqlNodeTemplete sqlNodeTemplete) {
            this.sqlNodeTemplete = sqlNodeTemplete;
        }

        @Override
        public ResultsCollective createSql(Object params) throws SqlTempleteIsNullException {
            if (this.sqlNodeTemplete == null) {
                throw new SqlTempleteIsNullException(this.getClass());
            }
            ResultsCollective resultsCollective = this.sqlNodeTemplete.createResultsCollective(params);
            return resultsCollective;
        }
    }
}
