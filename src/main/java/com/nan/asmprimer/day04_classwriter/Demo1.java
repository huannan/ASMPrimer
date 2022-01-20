package com.nan.asmprimer.day04_classwriter;

import com.nan.asmprimer.util.FileUtils;
import org.objectweb.asm.*;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

/**
 * 预期目标
 * <pre>
 * public interface HelloWorld {
 * }
 * </pre>
 */
public class Demo1 {

    private static byte[] dump() {
        ClassWriter cw = new ClassWriter(COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, "sample/HelloWorld", null, "java/lang/Object", null);

        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        byte[] bytes = dump();
        FileUtils.writeBytes(filePath, bytes);

        Class<?> clazz = Class.forName("sample.HelloWorld");
        System.out.println(clazz);
    }
}
