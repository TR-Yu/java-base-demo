package tech.tryu.thread.threadwaitnotify;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author YU
 * @date 2022-11-19 18:06
 * @since 1.0.0
 */
public class WaitNotifyTwo {
    static boolean flag = true;
    static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    /**
     * wait是指在一个已经进入了同步锁的线程内，让自己暂时让出同步锁，以便其他正在等待此锁的线程可以得到同步锁并运行，
     * 所以需要锁对象和调用的object.wait()对象是同一个。此时是让出锁，并且进入到等待状态
     * <p>
     * 其他线程调用了notify方法（notify并不释放锁，只是告诉调用过wait方法的线程可以去参与获得锁的竞争了，但不是马上得到锁，
     * 因为锁还在别人手里，别人还没释放），只有当退出了同步代码块以后，才可以竞争锁
     * 调用wait方法的一个或多个线程就会解除wait状态，
     * <p>
     * 所以需要调用，notify（）方法的线程执行退出锁区域内，
     */
    static class Wait implements Runnable {
        public void run() {
            // 加锁，拥有 lock的Monitor
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wai@ " + LocalDateTime.now());
                        lock.wait(200000);
                    } catch (InterruptedException e) {
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running@ " + LocalDateTime.now());
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + LocalDateTime.now());
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep@ " + LocalDateTime.now());
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
