package tech.tryu.functionpattern.methodparam;

/**
 * java 8 方法参数化示例
 *
 * @author YU
 * @date 2022-07-19 14:44
 */
public class MethodParamDemo {

    public static void main(String[] args) {
        Method.consumerFunction("123456yu", MethodParamDemo::print);
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
