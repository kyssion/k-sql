package org.ksql.script.bo;


import org.ksql.script.annotation.Delete;
import org.ksql.script.annotation.Insert;
import org.ksql.script.annotation.Select;
import org.ksql.script.annotation.Update;
import org.ksql.script.templete.SqlTemplete;
import org.mirror.reflection.agent.MethodAgent;

import java.util.List;

public class MapperMethod {
    private SqlType sqlType;
    private String baseSql;
    private SqlTemplete sqlTemplete;
    private List<Class<?>> baseParamsType;
    private Class<?> retObjectType;

    public MapperMethod(MethodAgent methodAgent){
        init(methodAgent);
    }

    private void init(MethodAgent methodAgent){
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

    public List<Class<?>> getBaseParamsType() {
        return baseParamsType;
    }

    public void setBaseParamsType(List<Class<?>> baseParamsType) {
        this.baseParamsType = baseParamsType;
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

    public class Param {
        private List paramItem;
        private Class<?> paramType;
        private String paramName;
        private boolean isArr;

        public boolean isArr() {
            return isArr;
        }

        public void setArr(boolean arr) {
            isArr = arr;
        }

        public List getParamItem() {
            return paramItem;
        }

        public void setParamItem(List paramItem) {
            this.paramItem = paramItem;
        }

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
