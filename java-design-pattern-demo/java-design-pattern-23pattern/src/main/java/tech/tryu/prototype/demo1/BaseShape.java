package tech.tryu.prototype.demo1;

import java.io.Serializable;

/**
 * 形状
 *
 * @author YU
 * @date 2022-05-01 0:14
 */
public class BaseShape implements Cloneable, Serializable {
    private double x;
    private double y;
    private String color;

    private TestEntity testEntity;

    public BaseShape(double x, double y, String color, TestEntity testEntity) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.testEntity = testEntity;
    }

    public BaseShape() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    @Override
    public BaseShape clone() {
        try {
            BaseShape clone = (BaseShape) super.clone();
            TestEntity entity = this.testEntity.clone();
            clone.setTestEntity(entity);
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
