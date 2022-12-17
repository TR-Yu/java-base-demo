package tech.tryu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tryu
 * @date 2022-12-16 14:05
 */
class MainTest {
    private final static Logger logger = LoggerFactory.getLogger(MainTest.class);

    @Test
    void mainTest() {
        logger.info("hello Junit5 test");
        Assertions.assertFalse(Boolean.FALSE);
        logger.info("end");
    }
}