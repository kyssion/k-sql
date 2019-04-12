package org.ksql.test;

import org.junit.ClassRule;
import org.junit.experimental.theories.DataPoint;
import org.ksql.script.annotation.Mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

public class FindTest {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<?> item = Find.class;

        item.getAnnotations();//
        item.getAnnotation(Mapper.class);
        item.getDeclaredAnnotations();
        item.getDeclaredAnnotation(Mapper.class);

        item.getAnnotationsByType(Mapper.class);
        item.getDeclaredAnnotationsByType(Mapper.class);

        Field field = item.getDeclaredField("test");

        Annotation annotation=field.getDeclaredAnnotation(Mapper.class);
        Annotation[] annotations= field.getDeclaredAnnotations();//获取所有的注解包括重复主机,不保留继承的注解
        Annotation[] annotations2=field.getAnnotations();//获取所有的注解.保留继承

        //这两个增加了对java8 重复注解的支持,Declared只是保证非继承
        Annotation[] annotations3= field.getAnnotationsByType(Mapper.class);//支持可重复注解
        Annotation[] annotations1=field.getDeclaredAnnotationsByType(Mapper.class);//返回直接存在于此元素上的所有注解。与此接口中的其他方法不同，该方法将忽略继承的注释

        AnnotatedType annotatedType=field.getAnnotatedType();
    }
}

class FindChild extends Find<String>{

}

class Find<T>{
    @ClassRule
    @DataPoint
    T test;
    public T method(){
        return this.test;
    }
}