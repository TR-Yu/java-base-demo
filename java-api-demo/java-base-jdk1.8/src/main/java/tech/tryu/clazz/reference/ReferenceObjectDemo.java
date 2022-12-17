package tech.tryu.clazz.reference;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * reference object test
 *
 * @author YU
 * @date 2022-10-28 21:47
 * @since 1.0.0
 */
public class ReferenceObjectDemo {
    public static void main(String[] args) {
        synchronizeLock();
    }

    /**
     * 该程序可以一直运行下去，因为List内元素的软引用可以在抛出OMM之前就回收掉，然后分配的空间也一起被回收，
     * 但是 一直 运行的 方法本身调用了的 list 则一直是被强引用的则不会被回收掉
     *
     * @param {@link }
     */
    private static void softReferenceTest() {
        new Thread(() -> {
            SoftReference<List<SoftReference<String>>> ref = new SoftReference<>(new ArrayList<>());
            List<SoftReference<String>> list = ref.get();
            while (true) {
                if (list != null) {
                    byte[] bytes = new byte[1024 * 1024];
                    list.add(new SoftReference<>(Arrays.toString(bytes)));
                    System.out.println("add one element " + list.size());
                } else {
                    // weak reference object is gc and exit
                    System.out.println("nothing");
                    return;
                }
            }
        }).start();
    }

    /**
     * Heap区溢出: linkedList 为 直接引用，则不断添加后耗尽Heap Space
     */
    private static void oomTest() {
        LinkedList<String> linkedList = new LinkedList<>();
        while (true) {
            byte[] bytes = new byte[1024 * 1024];
            linkedList.add(Arrays.toString(bytes));
        }
    }

    /**
     * NIO 直接获取内存，不通过Heap 内获取，同时，它也会被回收
     */
    private static void directMemoryTest() {

        while (true) {
            ByteBuffer.allocateDirect(1024 * 1024);
        }
    }

    /**
     * 创建两个锁对象，然后启动多个线程争用，导致deadlock
     *
     * jcmd
     */
    private static void synchronizeLock() {
        final Object lockA = new Object();
        final Object lockB = new Object();

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threads.add(
                    new Thread(() -> {
                        syncA(lockA, lockB);
                    })
            );

            threads.add(
                    new Thread(() -> {
                        syncB(lockA, lockB);
                    })
            );
        }

       threads.stream().parallel().forEach(Thread::start);
    }

    private static void syncA(final Object objectLockA, Object objectLockB) {
        synchronized (objectLockA) {
            System.out.println(" There is A , sync lock A");
            synchronized (objectLockB) {
                System.out.println("There is A , sync lock B");
            }
        }
    }

    private static void syncB(Object objectLockA, Object objectLockB) {
        synchronized (objectLockB) {
            System.out.println("There is B, sync lock B");
            synchronized (objectLockA) {
                System.out.println("There is A, sync lock A");
            }
        }
    }


}
