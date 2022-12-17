package tech.tryu.datastructure.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YU
 * @date 2022-12-01 14:32
 * @since 1.0.0
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(1,0.2f);
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 500; j++) {
                        map.put(Thread.currentThread().getName() + "-" + j, j + "");
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
            map.forEach((x, y) -> System.out.println(x));
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}