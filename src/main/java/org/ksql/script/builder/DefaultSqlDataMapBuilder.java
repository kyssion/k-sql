package org.ksql.script.builder;


import org.ksql.script.bo.ReturnParam;
import org.ksql.script.bo.SqlData;
import org.ksql.script.bo.SqlDataMap;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.agent.MethodAgent;
import org.mirror.reflection.mirror.MirrorClass;
import org.mirror.reflection.property.TypeParameterProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSqlDataMapBuilder implements SqlDataMapBuilder {
    @Override
    public Map<String, SqlData> build(SqlDataMap sqlDataMap) {
        Map<String, SqlData> methMap = new HashMap<>();
        MirrorClass mirrorClass = MirrorClass.forClass(sqlDataMap.getMapper());
        List<MethodAgent> list = mirrorClass.getAllMethod();

        for (MethodAgent agent : list) {
            Method method = agent.getMethod();
            SqlData sqlData = new SqlData();
            initReturnParam(sqlData, method);

        }

        return methMap;
    }

    private void initReturnParam(SqlData sqlData, Method method) {
        Class<?> returnType = (Class<?>) TypeParameterProcessor.processorReturnType(method);
        ReturnParam returnParam = null;
        Object object = null;
        try {
            object = returnType.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnParam = new ReturnParam(object,
                returnType, ResultSet.class);
        sqlData.setReturnParam(returnParam);
    }


}
