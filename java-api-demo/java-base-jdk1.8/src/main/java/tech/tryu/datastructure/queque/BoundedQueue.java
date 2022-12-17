package tech.tryu.datastructure.queque;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 上述示例中，BoundedQueue通过add(T t)方法添加一个元素，
 * 通过remove()方法移出一个元素。以添加方法为例。首先需要获得锁，
 * 目的是确保数组修改的可见性和排他性。当数组数量等于数组长度时，
 *
 * @author YU
 * @date 2022-11-30 18:21
 * @since 1.0.0
 */
public class BoundedQueue<T> {
    private final Object[] items;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    // 添加的下标，删除的下标和数组当前数量a
    private int addIndex, removeIndex, count;

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    /**
     * 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
     *
     * @param t
     * @throws InterruptedException
     */
    public T add(T t) throws InterruptedException {
        getLock();
        try {
            while (count == items.length) {
                notFull.await();
            }

            items[addIndex] = t;

            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    private void getLock() {
        lock.lock();
    }

    /**
     * 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
     *
     * @return
     * @throws InterruptedException
     */
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }
}