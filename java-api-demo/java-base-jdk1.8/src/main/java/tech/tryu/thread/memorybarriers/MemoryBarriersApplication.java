package tech.tryu.thread.memorybarriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 内存屏障测试
 *
 * @author YU
 * @date 2022-05-12 15:53
 */
public class MemoryBarriersApplication {
    private static final Logger logger = LoggerFactory.getLogger(MemoryBarriersApplication.class);

    public static void main(String[] args) {

        MemoryOperationThread operationThread = new MemoryOperationThread();

/*        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                operationThread.read();
            }).start();
            new Thread(() -> {
                operationThread.writer();
            }).start();

        }*/


        /**
         * 测试 unsafe.storeFence（）作用和 volatile 一样
         */

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                operationThread.readUnsafe();
            }).start();
            new Thread(() -> {
                operationThread.writeUnsafe();
            }).start();

        }


    }
}
