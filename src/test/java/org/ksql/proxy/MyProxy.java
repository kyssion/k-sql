package org.ksql.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {
    public static void main(String[] args) {
        Myproxy<TestInterface> myproxy = new Myproxy<>(new TestInterfaceImp());
        TestInterface testInterface = (TestInterface) Proxy.newProxyInstance(Proxy.class.getClassLoader(),new Class[]{TestInterface.class},myproxy);
        testInterface.say("test");
    }
}

interface TestInterface {
    String say(String he);
}

class TestInterfaceImp implements TestInterface {
    @Override
    public String say(String he) {
        System.out.println("this is " + he);
        return he;
    }
}

class Myproxy<T> implements InvocationHandler {
    private T testT;

    public Myproxy(T testT) {
        this.testT = testT;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object tiem = method.invoke(testT, args);
        System.out.println("end");
        return tiem;
    }
}