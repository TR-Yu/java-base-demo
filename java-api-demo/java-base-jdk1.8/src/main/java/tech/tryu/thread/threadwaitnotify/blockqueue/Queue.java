package tech.tryu.thread.threadwaitnotify.blockqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 阻塞队列
 *
 * @author YU
 * @date 2022-06-06 16:18
 */
public class Queue {

    /**
     * 队列实例
     */
    private List<String> entryList;

    /**
     * 队列容量
     */
    private int capacity;

    /**
     * 初始化队列
     */
    public Queue(int capacity) {
        if (capacity <= 0){
            throw new RuntimeException("capacity is litter than 0");
        }

        this.entryList = new LinkedList<>();
        this.capacity = capacity;

    }

    public void addEntryEnd(String message) {

        if (Objects.isNull(message)){
            throw new RuntimeException("message is null");
        }

        if (this.entryList == null) {
            throw new RuntimeException("Queue not init");
        }

        this.entryList.add(message);
    }

    public String getPushEntryFifo() {
        if (this.entryList == null || this.entryList.isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        return this.entryList.remove(0);
    }

    public int getCurrentCapacity() {
        return this.entryList.size();
    }

    public int getCapacity() {
        return this.capacity;
    }
}
