package tech.tryu.thread.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tryu
 * @date 2022-12-17 15:55
 */
class ConcurrentDemoTest {

    @Test
    public void interruptedWaitTest(){
        Interrupted.interruptedWait();
    }

}