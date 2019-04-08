package org.ksql;


import java.util.ArrayList;
import java.util.List;

public class TestXingneg {
    public static void main(String[] args) {
        test2();
    }

    public static void test2(){
        long iii = System.currentTimeMillis();
        List<TestItem> list = new ArrayList<>();
        for(int a=0;a<200000000;a++){
            TestItem testItem = new TestItem();
            rnn(testItem);
            list.add(testItem);
        }
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis()-iii);
    }

    public static void test1(){
        long iii = System.currentTimeMillis();
        List<Object> list = new ArrayList<>();
        for(int a=0;a<200000000;a++){
            Object testItem = new TestItem();
            rnn(testItem);
            list.add(testItem);
        }
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis()-iii);
    }

    public static void rnn(Object o){
        TestItem ooo = (TestItem) o;
        ooo.name="123";
        ooo.age="222";
    }
}

class TestItem{
    String name;
    String age;
}
class TestItem2 extends TestItem{
    String hhh;
    int ppp;
}