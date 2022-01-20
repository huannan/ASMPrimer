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
 * 预期目标
 * <pre>
 * public interface HelloWorld extends Cloneable {
 *     int LESS = -1;
 *     int EQUAL = 0;
 *     int GREATER = 1;
 *     int compareTo(Object o);
 * }
 * </pre>
 */
public class Demo2 {

    private static byte[] dump() {
        ClassWriter cw = new ClassWriter(COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, "sample/HelloWorld", null, "java/lang/Object", new String[]{"java/lang/Cloneable"});

        FieldVisitor fv1 = cw.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL, "LESS", "I", null, -1);
        fv1.visitEnd();

        FieldVisitor fv2 = cw.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL, "EQUAL", "I", null, 0);
        fv2.visitEnd();

        FieldVisitor fv3 = cw.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL, "GREATER", "I", null, 1);
        fv3.visitEnd();

        MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC | ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null);
        mv1.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        byte[] bytes = dump();
        FileUtils.writeBytes(filePath, bytes);

        Class<?> clazz = Class.forName("sample.HelloWorld");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field: " + field.getName() + "=" + field.get(null));
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method: " + method.getName());
        }
    }
}
