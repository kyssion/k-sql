package org.ksql;

import java.lang.reflect.Type;

public class RefTest {
    public static void main(String[] args) {
        Class<?> refItem = RefItemTestChildren.class;
        Type type = refItem.getGenericSuperclass();
    }
}

class RefItemTest<T>{

}

class RefItemTestChildren extends RefItemTest<String>{

}