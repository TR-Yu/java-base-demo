package tech.tryu.thread.threadmethod;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * join()测试
 * <p>
 * 没有 join()时：
 *
 * @author YU
 * @date 2022-11-01 12:39
 * @since 1.0.0
 */
public class JoinDemo {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        thread.start();
        thread.join(); // 主线程等待thread线程 die 以后 状态是 Waiting 然后向下执行
        System.out.println(Thread.currentThread().getName());

        Callable callable = new Callable() {
            @Override
            public Object call() {
                return "callable method";
            }
        };

        @SuppressWarnings("unchecked")
        FutureTask task = new FutureTask<Object>(callable);

        Thread thread1 = new Thread(task);
        thread1.start();
        Object o = task.get();
        System.out.println(o);

    }
}
