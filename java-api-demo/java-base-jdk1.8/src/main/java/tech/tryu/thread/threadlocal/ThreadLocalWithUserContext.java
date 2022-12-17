package tech.tryu.thread.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author YU
 * @date 2022-10-28 14:13
 * @since 1.0.0
 */
public class ThreadLocalWithUserContext implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalWithUserContext.class);

    private String name ;

    @Override
    public void run() {

        ThreadLocal<Context> userContext2 = ThreadLocal.withInitial(
                () -> {
                    return new Context("two");
                }
        );

        createThreadLocal();

        if ("zero".equals(this.name)) {
            logger.info(Thread.currentThread().getName() + " no threadLocal");
        } else{
            logger.info(Thread.currentThread().getName() + " before :");
            userContext2.set(new Context("good"));
            logger.info(Thread.currentThread().getName() + " after :");
        }

        while(true){
            try {
                TimeUnit.SECONDS.sleep(20);
                System.gc();
                return;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                userContext2.set(new Context("next"));
            }
        }




    }

    public ThreadLocalWithUserContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void createThreadLocal(){
        ThreadLocal<Context> userContext1 = ThreadLocal.withInitial(
                () -> {
                    return new Context("one");
                }
        );
        userContext1.set(new Context("terrible"));
    }
}
