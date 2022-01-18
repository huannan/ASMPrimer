package com.nan.asmprimer.day01_environment;

public class Demo {

    public static void main(String[] args) throws Exception {
        HelloWorldClassLoader classLoader = new HelloWorldClassLoader();
        Class<?> clazz = classLoader.loadClass("com.nan.asmprimer.day01_environment.HelloWorld");
        Object helloWorld = clazz.newInstance();
        System.out.println(helloWorld.toString());
    }
}
