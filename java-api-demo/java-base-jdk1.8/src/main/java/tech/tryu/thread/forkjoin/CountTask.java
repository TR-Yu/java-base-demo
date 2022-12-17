package tech.tryu.thread.forkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.LockSupport;

/**
 * @author YU
 * @date 2022-11-24 18:07
 * @since 1.0.0
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2; // 阈值
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start =start;
        this.end =end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end-start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end ; i++) {
                sum += i;
            }
            LockSupport.park(sum);
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();

            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }
}
