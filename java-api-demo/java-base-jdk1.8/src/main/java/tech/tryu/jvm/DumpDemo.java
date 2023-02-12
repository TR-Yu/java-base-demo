package tech.tryu.jvm;

/**
 * 需要设置 -XX:+PrintGC ，并观察dump的大小 判断是否已回收
 *
 * @author YU
 * @date 2022-10-26 15:46
 * @since 1.0.0
 */
public class DumpDemo {

    public static void main(String[] args) {
        DumpDemo dumpDemo = new DumpDemo();
        dumpDemo.testDumpGC04();
    }

    /**
     * 赋值以后，则触发不回收
     */
    private void testDumpGC01() {
        byte[] a = new byte[6 * 1024 * 1024];
    }


    private void testDumpGC02() {
        byte[] a = new byte[6 * 1024 * 1024];
        a = null; // 去掉引用
        System.gc();
    }
    /**
     *  a为作用域内，执行到下面以后，还是没有被回收，因为在testDumpGC03 变量表内被引用
     */
    private void testDumpGC03() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }

        System.gc();
    }

    private void testDumpGC04() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }

        int b = 1;
        System.gc();
    }

    private void testDumpGC05() {
        testDumpGC01(); // 这时候栈增出来后则被回收
        System.gc();
    }
}
