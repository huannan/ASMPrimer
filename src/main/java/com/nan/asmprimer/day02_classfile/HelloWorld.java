package com.nan.asmprimer.day02_classfile;

import java.io.Serializable;

public class HelloWorld implements Cloneable, Serializable {
    private static final int intValue = 10;

    public int test() {
        int a = 1;
        int b = 2;
        int c = a + b;
        return c;
    }
}