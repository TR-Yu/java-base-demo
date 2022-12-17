package tech.tryu.queque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tryu.datastructure.queque.DelayedElement;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author tryu
 * @date 2022-12-17 12:24
 */
class DelayQueueDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(DelayQueueDemoTest.class);


    /**
     * 测试 DelayedQueue 加入之后，是否会按照延迟时间依次出队
     */
    @Test
    public void testDelayQueueOrder() throws InterruptedException {

        // 设计参数
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<DelayedElement>();
        delayQueue.offer(new DelayedElement(1, 1L, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedElement(3, 4L, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedElement(2,3L, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedElement(4,10L, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedElement(6,30L, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedElement(5,20L, TimeUnit.SECONDS));

        // jol 打印出内存布局
        logger.info(GraphLayout.parseInstance(delayQueue).toPrintable());

        // 每次取验证顺序并输出打印内容
        for (int i = 1; i <= delayQueue.size(); i++) {
            DelayedElement element = delayQueue.take();
            Assertions.assertEquals(element.getContent(),i);
            // jol 打印每个值的布局
            logger.info(GraphLayout.parseInstance(element).toPrintable());
        }
    }
}