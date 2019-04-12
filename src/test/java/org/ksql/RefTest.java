package org.ksql;

import org.ksql.script.annotation.Mapper;
import org.mirror.reflection.property.TypeParameterResolver;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RefTest {
    public static void main(String[] args) throws NoSuchFieldException {
        Class<?> refItem = RefItemTestChildren.class;
        Type type = refItem.getGenericSuperclass();
        Type type1 = TypeParameterResolver.resolveFieldType(refItem.getSuperclass().getDeclaredField("list"),refItem);
        ParameterizedType type2 = (ParameterizedType) type1;
        Type[] types = type2.getActualTypeArguments();

        System.out.println(new HashMap<String,String>().getClass().getName());

        System.out.println(int[][][].class.getTypeName());

        System.out.println(refItem.getTypeName());

        System.out.println(refItem.getName());

        System.out.println(new ArrayList<String>().getClass().getName());

        System.out.println(new ArrayList<String>().getClass().getTypeName());

        refItem.getClassLoader();
        refItem.isAnnotation();//是否是注解
        refItem.isAnnotationPresent(Mapper.class);//是否是指定的注解
        refItem.isAnonymousClass();//是否是匿名类
        refItem.isArray();//是否是数组
        refItem.isAssignableFrom(Test.class);//如果调用类是参数的父类或者同一个类返回true
        refItem.isEnum();//是否是枚举
        refItem.isInstance(new Object());//确定指定的Object是否与此Class表示的对象分配兼容。相当于动态等效的instanceof
        refItem.isInterface();//是否是接口
        refItem.isLocalClass();//是否是局部类,就是在块中的类
        refItem.isPrimitive();//这个类是否是基本类型
        refItem.isSynthetic();//这个是个bug般的东西,看其他人的博客把https://blog.csdn.net/a327369238/article/details/52608805


        Field field=refItem.getField("xxx");//获取指定名称的field对象,只针对于public属性,包括父类
        refItem.getDeclaredField("");//获取名称的field,不限制类型和访问权限,只限于当前类
        refItem.getFields();//获取所有的public对象Field数组
        refItem.getDeclaredFields();//获取当前类型所有的field引用

        field.getType();//返回参数的class类型如果是泛型将会返回Object类型
        field.setAccessible(true);//设置为true表示可以对private参数尽心操作
        field.toGenericString();//返回参数名称 比如 T org.ksql.test.Find.test
        field.toString();//返回参数名称 java.lang.Object org.ksql.test.Find.test

        field.getAnnotatedType();
        field.getAnnotation(Mapper.class);
        field.getAnnotationsByType(Mapper.class);
        field.getAnnotations();
        field.getDeclaredAnnotations();
        field.getDeclaredAnnotation(Mapper.class);
        field.getDeclaringClass();
        field.getGenericType();
    }
}

class RefItemTest<T>{
    List<T> list;
}

class RefItemTestChildren<T> extends RefItemTest<T>{

}