package tech.tryu.datastructure.list;

import java.io.*;
import java.util.*;

/**
 * hashcode的 31 测试结果
 *
 * @author YU
 * @date 2022-10-22 18:28
 * @since 1.0.0
 */
public class HashCodeDemo {

    public static void main(String[] args) {
        HashCodeDemo demo = new HashCodeDemo();
        List<String> list = demo.readWord();
        HashSet<Integer> hashSet = new HashSet<>();
        list.forEach(el -> {
            int i = demo.hashCodeCompute(el, 34);
            if (hashSet.contains(i)) {
                System.out.println(el + " hashCode: " + i);
            }
            hashSet.add(i);
        });
    }


    private int hashCodeCompute(String word, int multiplier) {
        char[] value = word.toCharArray();
        int h = 0;
        if (value.length > 0) {
            for (int i = 0; i < value.length; i++) {
                h = multiplier * h + value[i];
            }
        }
        return h;
    }

    private List<String> readWord() {
        String path = Objects.requireNonNull(this.getClass().getResource("/enwords.txt")).getPath();
        BufferedReader br = null;
        List<String> strings = new LinkedList<>();
        try {
            br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return strings;
    }


}
