package tech.tryu.clazz.clazzloder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Java类加载器
 *
 * 类的生命周期：
 * 1.类的加载：获取到类的二进制数据
 *      1. 通过一个类的全限定名来获取其定义的二进制字节流 ：
 *          ** 通过类加载器加载 **
 *      2. 将二进制字节流的静态存储结构转化为方法区的运行时数据结构
 *      3. Java 堆中生成 java.lang.Class对象
 *
 * 2.连接阶段： 验证， 准备， 解析
 * 3.初始化阶段
 * 4.使用阶段
 * 5.卸载阶段
 *
 * 类的加载：
 * 1. 启动时 JVM 初始化加载 ：加载classpath下的所有的.class文件
 * 2. 通过 Class.forName() 动态加载
 * 3. ClassLoder.loadClass()方式加载
 * @author YU
 * @date 2022-07-05 9:48
 */
public class ClazzLoaderDemo {
    public static void main(String[] args) throws InterruptedException {
        try {
            Class<?> enumTestClazz = Class.forName("tech.tryu.datastructure.enums.EnumTest");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        printCurrentClassLoaderParent();
        TimeUnit.HOURS.sleep(1L);
    }

    /**
     *
     *
     * @author YU
     */
    public static void printCurrentClassLoaderParent(){
        Optional<ClassLoader> loaderOptional = Optional.ofNullable(Thread.currentThread().getContextClassLoader());
        printClassLoader(loaderOptional);
    }

    /**
     * 递归查找父类加载器并依次打印出来对应的父类的内容
     * 注意：顶层的启动类加载器，在hotspot虚拟机中是由C++实现所以展示不粗来
     *
     * @author YU
     * @param loaderOptional  {@link Optional<ClassLoader>}
     */
    public static void printClassLoader(Optional<ClassLoader> loaderOptional){
        loaderOptional.ifPresent(classLoader -> {
            System.out.println("the classLoader is " + classLoader);
            printClassLoader(Optional.ofNullable(classLoader.getParent()));
        });
    }
}
