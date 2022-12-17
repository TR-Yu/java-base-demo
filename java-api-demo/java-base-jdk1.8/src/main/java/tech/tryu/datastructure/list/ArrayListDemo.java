package tech.tryu.datastructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数组列表实现
 *
 * @author YU
 * @date 2022-10-22 15:35
 * @since 1.0.0
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        testSystemArrayCopy();
    }

    /**
     * 创建
     */
    private static void createMethod() {

        // 1. Arrays.asList(T ...)方法获取到的 ArrayList 为 Arrays$ArrayList的 add(), remove（）方法没有实现，（没继承 cloneable 接口，不可序列化）
        // 2. Arrays$ArrayList 方法 Arrays$ArrayList.toArray() 和 ArrayList.toArray() 方法获取到的 类对象数组不同
        // 原因，在创建的时候两者的 调用的 toArray() Jdk 8 中 是 a.clone()，则获取到的是元素本身类型，而 ArrayList 在构造时则是使用 Arrays.copyOf（） 中的类型为Object类型
        // 在 jdk 9 中已修复了 , 在 Arrays.toArray 中，Arrays.copyOf()转化为 Object 类型

        ArrayList<String> strings1 = new ArrayList<String>(){{
            add("one");
            add("two");
            add("three");
        }};

        List<String> strings = Arrays.asList("1", "2", "3");
        List<String> strList = new ArrayList<>(strings);

        System.out.println("Arrays.asList 转化" + (strings.toArray().getClass() == Object[].class) + (strings.toArray().getClass()));
        System.out.println("ArrayList 转化" +(strList.toArray().getClass() == Object[].class) + (strList.toArray().getClass()));

        List<Integer> integers = Collections.nCopies(10, 1);
        List<Integer> integerList = new ArrayList<>(integers);
        System.out.println("Collections.nCopies 转化" + (integers.toArray().getClass() == Object[].class) + (integers.toArray().getClass()));
        System.out.println("ArrayList 转化" +(integerList.toArray().getClass() == Object[].class) + (integerList.toArray().getClass()));
    }

    /**
     * ArrayList的底层结构就是 Array 所以，所有的增，删 都需要对数组的元素进行复制移动，system.arrayCopy()进行
     * 特别是大数据量下的元素的 增加和删除 效率低，因为会涉及到内存的拷贝移动 降低性能
     */
    private static void testSystemArrayCopy() {

        Object[] oldArray = {"1", "2", "3", "4"};
        Object[] newArray = new Object[oldArray.length + (oldArray.length >> 1)];
        System.arraycopy(oldArray,0,newArray, 0, oldArray.length);
        System.out.println(Arrays.toString(oldArray));
        System.out.println(Arrays.toString(newArray));

        System.out.println("--------------------------------");
        // 指定位置添加往后移动
        System.arraycopy(newArray,3, newArray, 4, oldArray.length - 3);
        newArray[3] = null;

        System.out.println(Arrays.toString(newArray));

        System.out.println("--------------------------------");
        // arrayList 删除的时候，不会对已扩容的list进行缩容。
        System.arraycopy(newArray,4,newArray,3,1);
        newArray[4] = null;
        System.out.println(Arrays.toString(newArray));

        System.out.println("u".hashCode());


    }

}
