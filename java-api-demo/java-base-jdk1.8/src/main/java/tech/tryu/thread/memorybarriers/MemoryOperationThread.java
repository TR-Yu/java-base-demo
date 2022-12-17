package tech.tryu.thread.memorybarriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 内存操作线程
 * volatile : 可见性：
 * - 读： 本缓存失效，从内存中重新获取
 * - 写： 将主内存失效，写回到主内存
 * - 所以 读写放在两个线程中，相当于通知
 * <p>
 * volatile : 禁止重排序：四种内存屏障 （memory fence)
 * - volatile 读：Load volatile / Load Load (禁止普通读和volatile读） Load Store (禁止 普通写 和 volatile 写）
 * - volatile 写：Store Store (禁止普通写） /Store volatile/ Store Load (禁止 volatile 读和写）
 * <p>
 * 起到相同作用的是 unsafe.loadfence()
 *
 * @author YU
 * @date 2022-05-12 15:53
 */
public class MemoryOperationThread {
    private static final Logger logger = LoggerFactory.getLogger(MemoryOperationThread.class);

    private static final Unsafe UNSAFE;

    static {
        try {
            UNSAFE = MemoryOperationThread.getUnsafe();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * volatileDemo data
     */
    private int x = 0;
    private int y = 1;
    private volatile boolean flag = false;

    private boolean unVolatile = false;


    /**
     * writer: volatile Store 则可见性来说是强制写入到内存中，有序性: 禁止普通写flag一定在后面
     * read: volatile Load 可见性则是从主内存加载，
     * 当存在线程竞争的时候，则只要打印出来一定是 x=42, y = 50;
     */
    public void writer() {
        x = 42;
        y = 50;
        flag = true;
    }

    public void read() {
        logger.info(String.format("flag: %b ; x: %d; y: %d", flag, x, y));
        if (flag) {
            logger.info("x: " + x);
            logger.info("y: " + y);
        }
    }

    public void writeUnsafe() {
        x = 42;
        y = 50;
        MemoryOperationThread.UNSAFE.storeFence();
        unVolatile = true;
    }

    public void readUnsafe() {
        logger.info(String.format("unVolatile: %b ; x: %d; y: %d", unVolatile, x, y));
        if (unVolatile) {
            logger.info("x: " + x);
            logger.info("y: " + y);
        }
    }

    /**
     * 只能通过 反射获取， 我记得还有一种可以利用JavaBean来获取，需要是一个JavaBean类型
     *
     * @return {@link sun.misc.Unsafe}
     */
    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe o = (Unsafe) theUnsafe.get(null);
        return o;
    }
}
