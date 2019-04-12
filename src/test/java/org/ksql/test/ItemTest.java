package org.ksql.test;

public class ItemTest {
    public static void main(String[] args) {
        Class<?> item = C.class;
        Class<?> cItem = item.getSuperclass();
    }
}
class A{

}
class B extends A{

}
class C extends B{

}
