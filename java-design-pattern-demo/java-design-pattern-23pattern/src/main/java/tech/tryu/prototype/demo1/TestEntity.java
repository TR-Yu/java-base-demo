package tech.tryu.prototype.demo1;

import java.io.Serializable;

/**
 * @author YU
 * @date 2022-07-28 23:48
 */
public class TestEntity implements Cloneable, Serializable {
    private int id;

    private String name;

    public TestEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public TestEntity clone() {
        try {
            return (TestEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
