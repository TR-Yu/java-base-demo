package tech.tryu.clazz.statiz;

/**
 * @author YU
 * @date 2022-10-28 12:18
 * @since 1.0.0
 */
public class DemoStatic {
    private static int id;

    private static char character;

    private int state;

    public DemoStatic(char character, int id, int state) {
        DemoStatic.character = character;
        DemoStatic.id = id;
        this.state = state;
    }

    public DemoStatic() {
    }

    @Override
    public String toString() {
        return "DemoStatic{" +
                "id=" + id +
                "; character=" + character +
                "; state=" + state +
                '}';
    }
}
