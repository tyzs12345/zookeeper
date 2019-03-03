package com.tian.zookeeper.FunctProgram;
import com.tian.zookeeper.Actiond;
public class lambda {

    public static void main(String[] args) {
        Actiond action = System.out :: println;
        action.execute("Hello World!");
        test(System.out :: println, "Hello World!");
    }

    static void test(Actiond action, String str) {
        action.execute(str);
    }
}
