package tech.tryu.thread.synchornizedlock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 同步锁测试
 *
 * synchronized :
 * 1. 编译为 字节码后，可以看到 monitorenter 和 monitorexit 这个也被称为 monitor lock JVM层实现
 * 2. 线程状态 status: waiting
 * 3. 简单使用，会出现的死锁问题：不同的线程加载持有对象容易出现的问题；解决方案使用 Lock 锁的 tryLock()方法
 * 4. 锁的升级： ** basic lock ** (JDk15以后需要开启） , thin lock,  heavy lock; 及对应的锁的升级过程和锁特殊使用时的升级过程
 * 5. 锁的存放：Object Header : mark word, kclass point, alignemnt gap
 * @author YU
 * @date 2022-06-11 16:46
 */
public class SynchronizedDemo {
    private static Logger logger = Logger.getLogger("SynchronizeDemo");
    public static void main(String[] args) throws InterruptedException {
        testLocksObject();
    }
    private static  void testThinLocksWith() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object lockObject = new Object();
        logger.info("未进入同步块, MarkWord :");
        logger.info(ClassLayout.parseInstance(lockObject).toPrintable());
        Runnable runnable = () -> {
            synchronized (lockObject) {
                logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
                logger.info(ClassLayout.parseInstance(lockObject).toPrintable());
            }
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.scheduleAtFixedRate(runnable,0,5,TimeUnit.SECONDS);
    }

    private static void testLocksObject() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        logger.info("未进入同步块, MarkWord :");
        logger.info(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
            logger.info(ClassLayout.parseInstance(o).toPrintable());
        }

        Thread thread = new Thread(() -> {
            synchronized (o) {
                logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
                logger.info(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        thread.start();
        thread.join();
        logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
        logger.info(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
            logger.info(ClassLayout.parseInstance(o).toPrintable());
        }

        synchronized (o) {
            logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
            logger.info(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
