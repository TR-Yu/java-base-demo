package tech.tryu.functionpattern.stream.enitity;

/**
 * 指甲
 *
 * @author tryu
 * @date 2022-12-12 14:29
 */
public class Finger {
    private Nails nails;

    private String fingerName;

    public Finger(Nails nails, String fingerName) {
        this.nails = nails;
        this.fingerName = fingerName;
    }

    public Nails getNails() {
        return nails;
    }

    public void setNails(Nails nails) {
        this.nails = nails;
    }

    public String getFingerName() {
        return fingerName;
    }

    public void setFingerName(String fingerName) {
        this.fingerName = fingerName;
    }

    @Override
    public String toString() {
        return "Finger{" +
                "nails=" + nails +
                ", fingerName='" + fingerName + '\'' +
                '}';
    }
}
