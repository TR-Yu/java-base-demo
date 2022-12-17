package tech.tryu.functionpattern.stream.enitity;

import java.math.BigDecimal;

/**
 * 人，
 *
 * @author tryu
 * @date 2022-12-12 14:28
 */
public class People {

    private String name;

    private Integer old;

    private Hand hand;

    private BigDecimal money;

    public People() {
    }

    public People(String name, Integer old, Hand hand, BigDecimal money) {
        this.name = name;
        this.old = old;
        this.hand = hand;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", old=" + old +
                ", hand=" + hand +
                ", money=" + money +
                '}';
    }
}
