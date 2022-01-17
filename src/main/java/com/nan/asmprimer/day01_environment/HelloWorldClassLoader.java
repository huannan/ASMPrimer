package com.nan.asmprimer.day01_environment;

/**
 * 自定义ClassLoader，专门用于加载HelloWorld
 */
public class HelloWorldClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("com.nan.asmprimer.day01_environment.HelloWorld".equals(name)) {
            byte[] bytes = HelloWorldDump.dump();
            return defineClass(name, bytes, 0, bytes.length);
        }
        throw new ClassNotFoundException("Class not found: " + name);
    }
}
