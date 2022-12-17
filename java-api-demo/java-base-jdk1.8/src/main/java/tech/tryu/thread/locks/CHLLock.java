package tech.tryu.thread.locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 锁的接口
 * CHL 公平锁 自旋锁
 * 不可重入
 *
 * @author YU
 * @date 2022-06-08 13:26
 */
public class CHLLock implements Lock{

    /**
     * 尾节点引用,是所有线程共同引用的，
     */
    private final AtomicReference<QNode> tail;

    /**
     * 当前节点，线程内变量，每个线程都有
     */
    private final ThreadLocal<QNode> myNode;

    /**
     * 前驱节点 线程内变量，每个线程都有
     */
    private final ThreadLocal<QNode> myPred;

    public CHLLock() {
        // 赋值原子引用，更新过程原子化
        this.tail = new AtomicReference<>(new QNode());
        // ThreadLocalMap 中 myNode 为 key, value 为 新的 QNode {locked = false}
        this.myNode = ThreadLocal.withInitial(QNode::new);
        // ThreadLocalMap 中 myPred 为 key, value 为 null
        this.myPred = ThreadLocal.withInitial(()-> null);
    }

    /**
     * 而且还存在的问题是一个线程中只能使用一次 lock(),要是多次使用则尾指针会从新指向最后一个线程的Node，则所有的线程都在自旋
     */
    @Override
    public void lock() {
        // 获取当前线程的 myNode 初始化时为 false
        QNode currentNode = myNode.get();
        // 将 value 赋值为 true
        currentNode.locked = true;

        // 操作将tail（原子操作） 和 myPre 值 同时设置为currentNode;
        QNode predNode = tail.getAndSet(currentNode);
        myPred.set(predNode);
        while (predNode.locked) {

        }
    }

    /**
     * 操作是幂等的对于同一个线程连续使用的时候，效果一样都是同一个值，myNode和 myPred 内的Node
     */
    @Override
    public void unlock() {
        QNode currentNode = myNode.get();
        currentNode.locked = false;
        // myNode 内的node 指向前一个线程指向的myNode的CurrentNode,
        myNode.set(myPred.get());
    }

    private static class QNode{
        volatile boolean locked = false;
    }
}
