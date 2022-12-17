package tech.tryu.thread.threadlocal;

/**
 * 线程缓存
 *
 * @author YU
 * @date 2022-10-28 14:11
 * @since 1.0.0
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocalWithUserContext first = new ThreadLocalWithUserContext("three");
        ThreadLocalWithUserContext second = new ThreadLocalWithUserContext("four");
        ThreadLocalWithUserContext zero = new ThreadLocalWithUserContext("zero");

        new Thread(first).start();
        new Thread(second).start();
        new Thread(zero).start();
    }
}
