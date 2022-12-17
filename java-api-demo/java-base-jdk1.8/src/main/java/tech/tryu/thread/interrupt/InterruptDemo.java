package tech.tryu.thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 如何终止一个线程？
 * 中断时的线程的状态有 1. 运行中的， 2.处于阻塞中的，
 * {@link Thread#interrupt()} 方法使用，
 * <p>
 *       public void interrupt() {
 *         if (this != Thread.currentThread())
 *             checkAccess();
 *
 *         synchronized (blockerLock) {
 *             Interruptible b = blocker;
 *             if (b != null) {
 *                 interrupt0();           // Just to set the interrupt flag
 *                 b.interrupt(this);
 *                 return;
 *             }
 *         }
 *         interrupt0();
 *     }
 * </p>
 * 设置 Thread 中的 native 的中断标识为 true  interrupt0();
 * 需要对状态标识的判断则运行时才会使用到这个标识否则设置无效
 *
 * 同时对于处于 Blocking 状态的则直接抛错，则调用方需要做捕获异常处理
 *
 *
 * @author YU
 * @date 2022-06-07 0:07
 */
public class InterruptDemo {
    private static final Logger logger = LoggerFactory.getLogger(InterruptDemo.class);
    public static void main(String[] args) {

        InterruptDemo interruptDemo = new InterruptDemo();
        /*try {
            interruptDemo.simpleInterrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        interruptDemo.daemonDemo();
    }


    /**
     * {@link Thread#interrupt()} 在正常线程运行下使用样例
     *
     * @throws InterruptedException
     */
    private void simpleUseInterruptMethod() throws InterruptedException {


        // 子线程
        Thread thread = new Thread(() -> {
            while (true) {
                logger.info("run run running");
                //标识位是否设置为了true,是则中断运行
                if (Thread.currentThread().isInterrupted()) {
                    logger.info(Thread.currentThread().getName() + "exit the thread run");
                    break;
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        TimeUnit.SECONDS.sleep(1);
        /* 打印出来的是 false 和 simpleInterrupt() 做对比*/
        logger.info(String.valueOf(thread.isInterrupted()));

    }

    private void simpleInterrupt() throws InterruptedException {

            Thread thread1 = new Thread(() -> {
            try {
                logger.info("清空前打印：interrupted: " + Thread.currentThread().isInterrupted());
                TimeUnit.SECONDS.sleep(120);
            } catch (InterruptedException e) {
                // 清除中断标识，在抛出InterruptedException后，所以需要重新设置下标识
                logger.info(Thread.currentThread().getName() + " exit the thread run");
                logger.info("清空前打印：interrupted: " + Thread.currentThread().isInterrupted());
            }
        });
        thread1.start();

        TimeUnit.SECONDS.sleep(10);
        thread1.interrupt();
        TimeUnit.SECONDS.sleep(10);
        // 返回的也是 false 原因是sleep（）方法引起中断后，会先将中断状态清除然后再抛出异常
        logger.info(String.valueOf(thread1.isInterrupted()));

    }
    private void daemonDemo() {
       logger.info("daemon start");

        Runnable running = () -> {
            while (true) {
                logger.info("call is running");
            }
        };

        Thread thread = new Thread(running);

        /**
         * 将 thread 设置为守护线程，则在其它线程退出以后，该线程会自动退出，
         * 所以打印不出 running 内的内容
         *
         */
        thread.setDaemon(true);
        thread.start();

       logger.info("daemon end");
    }
}
