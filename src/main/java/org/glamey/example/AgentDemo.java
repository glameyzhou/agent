package org.glamey.example;

/**
 * @author yang.zhou 2019-09-09.10
 */
public class AgentDemo {


    public static void main(String[] args) {
        System.out.println("main");
        doMethod_1(new String[]{"v1"});
        doMethod_2(new String[]{"v1"});
        doMethod_3(new String[]{"v1"});
    }


    public static void doMethod_1(String[] args) {
        System.out.println("do method v1");
    }

    public static void doMethod_2(String[] args) {
        System.out.println("do method v2");
    }

    public static void doMethod_3(String[] args) {
        System.out.println("do method v3");
    }
}
