package tech.tryu.clazz.statiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * static variable  and method for test
 *
 * @author YU
 * @date 2022-10-28 12:18
 * @since 1.0.0
 */
public class StaticDemo {
    private static final Logger logger = LoggerFactory.getLogger(StaticDemo.class);
    public static void main(String[] args) {
        DemoStatic demoStatic = new DemoStatic();
        logger.info(demoStatic.toString());
        DemoStatic demoStatic1 = new DemoStatic('1', 1, 10);
        logger.info(demoStatic1.toString());
    }
}
