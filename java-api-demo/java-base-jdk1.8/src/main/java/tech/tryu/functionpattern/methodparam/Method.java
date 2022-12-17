package tech.tryu.functionpattern.methodparam;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 方法调用
 *
 * @author YU
 * @date 2022-07-19 14:45
 */
public class Method {

    protected static void consumerFunction(Object message, Consumer<String> consumerAction) {
        // 对message进行校验是否为String, 然后转为 String，用consumer去解决
        if (!(message instanceof String) || consumerAction == null) {
            return;
        }
        String messageStr = (String) message;
        consumerAction.accept(messageStr);
    }

    protected static void biFunction(Object message, BiConsumer<String, Integer> printAction) {
        if (!(message instanceof String) || printAction == null) {
            return;
        }
    }
}
