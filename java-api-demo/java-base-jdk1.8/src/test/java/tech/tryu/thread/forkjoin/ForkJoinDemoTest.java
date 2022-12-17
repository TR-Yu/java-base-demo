package tech.tryu.thread.forkjoin;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author YU
 * @date 2022-11-24 18:03
 * @since 1.0.0
 */
public class ForkJoinDemoTest {
    private final static Logger logger = LoggerFactory.getLogger(ForkJoinDemoTest.class);

    /**
     * 发现与 forkJoinPool 的线程有
     * main() 函数，result.get() 来阻塞等待获取结果
     * fo (;;) 的目的是为了防止test执行后，会停止其它线程
     */
    @Test
    public void ThreadPoolTest() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 2);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
       /* try {
            logger.info(String.valueOf(result.get()));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }*/
        for (; ; ) ;
    }
}