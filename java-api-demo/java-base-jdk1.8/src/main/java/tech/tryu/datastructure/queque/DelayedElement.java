package tech.tryu.datastructure.queque;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author YU
 * @date 2022-10-23 8:35
 * @since 1.0.0
 */
public class DelayedElement  implements Delayed {

    private final Integer content;

    private final Long time;

    public DelayedElement(Integer content, Long time, TimeUnit timeUnit) {
        this.content = content;
        this.time = System.currentTimeMillis() + (time > 0 ? timeUnit.toMillis(time) : 0);
    }

    @Override
    public long getDelay( TimeUnit unit) {
        assert unit != null;
        return time - System.currentTimeMillis() ;
    }

    @Override
    public int compareTo( Delayed o) {
        assert o != null;
        DelayedElement outer = (DelayedElement) o;
        long diff = this.time - outer.time;
        return diff <= 0 ? -1 : 1;
    }

    public Integer getContent() {
        return content;
    }

}
