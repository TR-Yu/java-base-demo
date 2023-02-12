package tech.tryu.jvm;

/**
 * @author YU
 * @date 2022-10-26 18:16
 * @since 1.0.0
 */
public class StackAllocDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
    }

    static class A {

        Integer id = 0;

        String name = "";

        public A(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private static void alloc() {
        A a = new A(1, "yu");
    }
}
