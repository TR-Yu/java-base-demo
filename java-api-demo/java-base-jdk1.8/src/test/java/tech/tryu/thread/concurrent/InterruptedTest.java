package tech.tryu.thread.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tryu
 * @date 2022-12-17 15:58
 */
class InterruptedTest {
    @Test
    void interruptedActivityTest() {
        Interrupted.interruptedActivity();
    }

    @Test
    void interruptedWaitTest() {
        Interrupted.interruptedWait();
    }
}