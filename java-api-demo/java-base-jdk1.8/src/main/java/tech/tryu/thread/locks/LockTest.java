package tech.tryu.thread.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试锁
 *
 * @author YU
 * @date 2022-11-01 14:08
 * @since 1.0.0
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        CHLLock lock = new CHLLock();
        ModifiedObject object = new ModifiedObject();
        ReentrantLock reentrantLock = new ReentrantLock();
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                lockTest.getAndSetValueCHLLock(lock,object);
            });
            thread.start();
        }

    }


    /**
     * 锁是否时起作用的。
     *
     * @param lock
     * @param object
     */
    private void getAndSetValueReentrantLock(Lock lock, ModifiedObject object) {
        lock.lock();
        try {
            int i = object.getI() + 1;
            object.setI(i);
            System.out.println("I am coming, i = " + i);
        }finally {
            lock.unlock();
        }
    }

    private void getAndSetValueCHLLock(CHLLock lock, ModifiedObject object) {
        lock.lock();
        try {
            int i = object.getI() + 1;
            object.setI(i);
            System.out.println("I am coming, i = " + i);
        lock.lock();
        }finally {
            lock.unlock();
        }

        lock.lock();

        lock.unlock();
    }

}
