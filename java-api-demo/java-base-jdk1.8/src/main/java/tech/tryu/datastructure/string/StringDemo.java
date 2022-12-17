package tech.tryu.datastructure.string;

/**
 * 字符串String 优化问题
 *
 * @author YU
 * @date 2022-10-28 14:40
 * @since 1.0.0
 */
public class StringDemo {
    public static void main(String[] args) {

    }

    private String addBatchOperator() {
        String a = "";
        a += "1" + ";" + "2" + ";";
        return a;
    }

    private String addSingeOperator() {
        String b = "";
        b  += "a";
        b  += "b";
        b  += "c";
        b  += "d";
        b  += "e";
        return b;
    }
}
