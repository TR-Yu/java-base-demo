package tech.tryu.thread.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 *
 * @author YU
 */
public class ExecutorDemo {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        int CPU_CORE = Runtime.getRuntime().availableProcessors();
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(CPU_CORE);
        ScheduledFuture<String> schedule = threadPoolExecutor.schedule(() -> {
            logger.info("\nthe weather is bad!!!");
            return "1";
        }, 10, TimeUnit.SECONDS);
        String s = schedule.get(300, TimeUnit.SECONDS);
        logger.info("\ninfo: " + s);

    }

}
