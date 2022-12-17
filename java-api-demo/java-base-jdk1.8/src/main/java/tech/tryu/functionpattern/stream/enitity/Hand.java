package tech.tryu.functionpattern.stream.enitity;

/**
 * 手
 *
 * @author tryu
 * @date 2022-12-12 14:29
 */
public class Hand {
    private Finger finger;

    private String HandName;

    public Hand(Finger finger, String handName) {
        this.finger = finger;
        HandName = handName;
    }

    public Hand(String handName) {
        HandName = handName;
    }

    public Finger getFinger() {
        return finger;
    }

    public void setFinger(Finger finger) {
        this.finger = finger;
    }

    public String getHandName() {
        return HandName;
    }

    public void setHandName(String handName) {
        HandName = handName;
    }
}
