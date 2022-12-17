package tech.tryu.functionpattern.stream;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Stream;

/**
 * @author YU
 * @date 2022-11-25 23:05
 * @since 1.0.0
 */
public class StreamDemo {

    static {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
    }

    public static void main(String[] args) {

    }

    public void parallelStream() {
        Stream.of(1, 2, 3, 4, 5, 8, 9, 10, 11, 23)
                .filter(el -> {
                    System.out.println(el);
                    return el > 3;
                })
                .forEach(el -> {
                    System.out.println(el);
                    LockSupport.park();
                });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 2, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });
    }


    public void findAnyStream() {
        // 即时使用了并行流，也会在map() 中等待所有的任务完成之后，再找到一个
        Optional<Integer> optional = Stream.of(0, 10)
                .parallel().map(el -> {
                    try {
                        TimeUnit.SECONDS.sleep(el);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return el;
                }).findAny();
        optional.ifPresent(System.out::println);
    }

}
