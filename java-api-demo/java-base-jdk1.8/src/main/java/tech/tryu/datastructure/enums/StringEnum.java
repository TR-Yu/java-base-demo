package tech.tryu.datastructure.enums;

/**
 * @author YU
 * @date 2022-09-05 11:03
 */
public enum StringEnum {

    ONE_STRING("one"),

    TWO_STRING("two");

    private String name;

    StringEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
