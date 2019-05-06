package org.ksql;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Test.class.getMethod("hhhh",String.class);
        Parameter[] p = method.getParameters();
        Class<?> iii  = (Class<?>) p[0].getParameterizedType();
    }

    public StringBuffer hhhh(String name){
        return new StringBuffer();
    }
}
