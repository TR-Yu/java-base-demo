package tech.tryu.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 测试interrupted中断机制
 * 主线程 - 子线程
 * |调用了等待|
 *
 * @author YU
 * @date 2022-08-24 13:32
 */
public class Interrupted {
    private static Logger logger = Logger.getLogger("Interrupted");

    /**
     * 线程处于活动中时，在主线程中调用thread01.interrupt()时，则，需要获取到中断标识然后做出行动
     *
     * @author YU
     */
    public static void interruptedActivity() {

        // 处于活动中的时候，由主线程调用该线程的中断函数，表现结果
        Thread thread01 = new Thread(() -> {
            while (true) {
                logger.info("activity");
                if (Thread.interrupted()) {
                    logger.info("interrupted");
                    logger.info(String.valueOf(Thread.currentThread().isInterrupted()));
                    break;
                }
            }
        });
        thread01.start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread01.interrupt();
        logger.info(String.valueOf(thread01.isInterrupted()));
    }


    public static void  interruptedWait() {
        Thread thread02 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                logger.info("sleep is stop");
            }

            logger.info("activity");

        });
        thread02.setDaemon(true);
        thread02.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread02.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info(String.valueOf(thread02.isInterrupted()));
    }
}


