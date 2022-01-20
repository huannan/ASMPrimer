package com.nan.asmprimer.day03_asmprint;

public class HelloWorld {

    static {
        System.out.println("aaa");
    }

    {
        System.out.println("bbb");
    }

    public void test() {
        System.out.println("Hello world from asm");
    }
}