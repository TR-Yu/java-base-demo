package tech.tryu.datastructure.hash;

import java.util.LinkedHashMap;

/**
 * @author YU
 * @date 2022-08-23 0:44
 */
public class HashDemo {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(10, 0.75f, false);
        linkedHashMap.put("3", 4);
        linkedHashMap.put("1", 1);
        linkedHashMap.put("2", 2);
        linkedHashMap.put("3", 41);
        linkedHashMap.forEach((key, value) -> {
            System.out.printf("key: %s, value: %d\n", key, value);
        });
    }

}
