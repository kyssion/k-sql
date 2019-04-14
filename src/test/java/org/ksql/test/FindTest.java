package org.ksql.test;

import org.junit.ClassRule;
import org.junit.experimental.theories.DataPoint;
import org.ksql.script.annotation.Mapper;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;

public class FindTest {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> item = Find.class;

        item.getAnnotations();//获取所有的注解(包括继承的注解,但是并不包括重复注解)

        Method method =
                item.getMethod("method", new Class[]{Object.class});
        item.getMethods();
        item.getDeclaredMethod("method", new Class[]{Object.class});
        item.getDeclaredMethods();
        item.getEnclosingMethod();//返回这个封闭类的所有方法

        method.getGenericParameterTypes();//返回参类型的数组
        method.getParameterTypes();//返回参数类型class数组

        method.getTypeParameters();//返回 <S> T method S对应的信息
        method.getParameterCount();//返回参数数量

        method.getGenericReturnType();//获取变量的type类型
        method.getReturnType();//获取变量的返回值class

        method.invoke(new Object(), new Object[]{});

        method.getParameterAnnotations();
        method.getDeclaredAnnotations();
        method.getDeclaredAnnotation(Mapper.class);
        method.getDeclaredAnnotationsByType(Mapper.class);

        method.getDefaultValue();
        method.getName();
        method.getModifiers();
        method.isBridge();
        method.isDefault();
        method.isSynthetic();
        method.isVarArgs();

        item.getConstructor(String.class);
        item.getConstructor();
        item.getConstructors();
        item.getDeclaredConstructor(String.class);
        item.getDeclaredConstructors();

        Constructor constructor = item.getConstructor();

    }
}

class FindChild extends Find<String> {

}

class Find<T> {
    @ClassRule
    @DataPoint
    T test;

    public <S> T method(T name) {
        return this.test;
    }
}