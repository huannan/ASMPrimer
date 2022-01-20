package com.nan.asmprimer.day04_classwriter;

import com.nan.asmprimer.util.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

/**
 * 预期目标（ASM需要手动添加无参数的构造方法）
 * <pre>
 * public class HelloWorld {
 * }
 * </pre>
 */
public class Demo3 {

    private static byte[] dump() {
        ClassWriter cw = new ClassWriter(COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/HelloWorld", null, "java/lang/Object", null);

        MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv1.visitCode();
        mv1.visitVarInsn(ALOAD, 0);
        mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv1.visitInsn(RETURN);
        mv1.visitMaxs(1, 1);
        mv1.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        byte[] bytes = dump();
        FileUtils.writeBytes(filePath, bytes);

        Class<?> clazz = Class.forName("sample.HelloWorld");
        clazz.newInstance();
    }
}
