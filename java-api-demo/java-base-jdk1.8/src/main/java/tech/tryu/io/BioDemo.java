package tech.tryu.io;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Java 的 阻塞IO Java实现
 *
 * @author YU
 * @date 2022-07-12 22:29
 */
public class BioDemo {
    private final static Logger LOGGER = Logger.getLogger("BioDemo");

    public static void main(String[] args) {
        BioDemo bioDemo = new BioDemo();
        bioDemo.parameterizedBehavior(System.out::println, "爱我中华");
    }

    private void parameterizedBehavior(Consumer<String> command, String param) {
        LOGGER.info("执行参数化行为函数");
        command.accept(param);
        LOGGER.info("执行参数化后");
    }
}
