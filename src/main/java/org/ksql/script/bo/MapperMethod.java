package org.ksql.script.bo;


import org.ksql.script.annotation.*;
import org.ksql.script.templete.SqlTemplete;
import org.mirror.reflection.agent.MethodAgent;
import org.mirror.reflection.mirror.MirrorClass;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperMethod {
    private SqlType sqlType;
    private String baseSql;
    private SqlTemplete sqlTemplete;
    private List<ParamItem> params;
    private Class<?> retObjectType;

    public MapperMethod(MethodAgent methodAgent) {
        init(methodAgent);
        this.params = initParams(methodAgent);
    }

    private List<ParamItem> initParams(MethodAgent methodAgent) {
        List<ParamItem> list = new ArrayList<>();
        Parameter[] parameters = methodAgent.getParams();
        for (Parameter parameter : parameters) {
            ParamItem paramItem = new ParamItem();
            Class<?> paramType = (Class<?>) parameter.getParameterizedType();
            Param param = parameter.getAnnotation(Param.class);
            if (param != null) {
                paramItem.setParamName(param.value());
            } else {
                paramItem.setParamName(paramType.getName());
            }
            paramItem.setParamType(paramType);
            list.add(paramItem);
        }
        return list;
    }

    private void init(MethodAgent methodAgent) {
        if (methodAgent.hasAnnotation(Select.class)) {
            this.setBaseSql(methodAgent.getAnnotation(Select.class).value());
            this.setSqlType(SqlType.SELECT);
        } else if (methodAgent.hasAnnotation(Update.class)) {
            this.setBaseSql(methodAgent.getAnnotation(Update.class).value());
            this.setSqlType(SqlType.UPDETE);
        } else if (methodAgent.hasAnnotation(Insert.class)) {
            this.setBaseSql(methodAgent.getAnnotation(Insert.class).value());
            this.setSqlType(SqlType.INSERT);
        } else if (methodAgent.hasAnnotation(Delete.class)) {
            this.setBaseSql(methodAgent.getAnnotation(Delete.class).value());
            this.setSqlType(SqlType.DELETE);
        }
    }

    public SqlTemplete getSqlTemplete() {
        return sqlTemplete;
    }

    public void setSqlTemplete(SqlTemplete sqlTemplete) {
        this.sqlTemplete = sqlTemplete;
    }

    public SqlType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
    }

    public Class<?> getRetObjectType() {
        return retObjectType;
    }

    public void setRetObjectType(Class<?> retObjectType) {
        this.retObjectType = retObjectType;
    }

    public String getBaseSql() {
        return baseSql;
    }

    public void setBaseSql(String baseSql) {
        this.baseSql = baseSql;
    }

    public Map<String, Object> createParamObject(Object[] params) {
        Map<String, Object> map = new HashMap<>();
        for (Object param : params) {
            MirrorClass mirrorClass = MirrorClass.forClass(param.getClass());
            for (ParamItem item : this.params) {
                if(item.getParamType()==mirrorClass.getType()){
                    map.put(item.getParamName(),param);
                    break;
                }
            }
        }
        return map;
    }

    public class ParamItem {
        private Class<?> paramType;
        private String paramName;

        public Class<?> getParamType() {
            return paramType;
        }

        public void setParamType(Class<?> paramType) {
            this.paramType = paramType;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
    }
}
