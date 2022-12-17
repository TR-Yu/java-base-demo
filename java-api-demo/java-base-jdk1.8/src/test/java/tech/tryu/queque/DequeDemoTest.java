package tech.tryu.queque;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tryu
 * @date 2022-12-17 14:43
 */
class DequeDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(DelayQueueDemoTest.class);

    @Test
    void arrayDeque() {
        ArrayDeque<String> stringDeque = new ArrayDeque<>(3);
        stringDeque.offerFirst("1");
        stringDeque.offerLast("-1");
        stringDeque.offerFirst("2");
        stringDeque.offerLast("-2");
        stringDeque.offerFirst("3");
        stringDeque.offerLast("-3");
        stringDeque.offerFirst("4");
        stringDeque.offerLast("-4");

        PrintWriter pw = new PrintWriter(System.out, true);
        String current = GraphLayout.parseInstance(stringDeque).toPrintable();
        System.out.println("\n current:\n" + current);
        System.out.println("依次 pollFirst 出栈：\n" + stringDeque.pollFirst());
        System.out.println("依次 pollList 出栈：\n" + stringDeque.pollLast());
        System.out.println("出栈后：" + GraphLayout.parseInstance(stringDeque).toPrintable());

        pw.close();
    }

    /**
     * 双向对列
     * 模拟出队列，然后扩容了，是否可以正常的FIFO
     * 采用 offerFirst  pollLast 来进行 队列头入队， 队列尾部出队，实际是数组的0位置为队尾，
     * 数组最大值为队首；当head = tail 的时候可需要扩容
     */
    @Test
    void dequeResizeAndMoinQueue() {
        int initSize = 3;
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(initSize);
        arrayDeque.push(1);
        arrayDeque.push(2);
        arrayDeque.push(3);
        arrayDeque.push(4);
        arrayDeque.push(5);
        arrayDeque.push(6);
        arrayDeque.push(7);
        arrayDeque.push(8);
        arrayDeque.push(9);
        arrayDeque.push(10);
        arrayDeque.push(11);
        arrayDeque.push(12);
        arrayDeque.push(13);
        arrayDeque.push(14);
        arrayDeque.push(15);
        arrayDeque.push(16);
        arrayDeque.push(17);

        logger.info(GraphLayout.parseInstance(arrayDeque).toPrintable());

        while (arrayDeque.peekLast() != null) {
            System.out.println(arrayDeque.pollLast());
        }

        System.out.println(GraphLayout.parseInstance(arrayDeque).toPrintable());
    }
}