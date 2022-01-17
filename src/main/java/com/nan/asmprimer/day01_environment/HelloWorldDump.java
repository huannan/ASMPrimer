package com.nan.asmprimer.day01_environment;

import org.objectweb.asm.*;

public class HelloWorldDump implements Opcodes {

    public static byte[] dump() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/nan/asmprimer/day01_environment/HelloWorld", null, "java/lang/Object", null);

        // 添加构造方法
        MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv1.visitCode();
        mv1.visitVarInsn(ALOAD, 0);
        mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv1.visitInsn(RETURN);
        mv1.visitMaxs(1, 1);
        mv1.visitEnd();

        // 添加toString方法
        MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
        mv2.visitCode();
        mv2.visitLdcInsn("Hello world from asm");
        mv2.visitInsn(ARETURN);
        mv2.visitMaxs(1, 1);
        mv2.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }
}