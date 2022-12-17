package tech.tryu.functionpattern.stream.enitity;

/**
 * 指甲
 *
 * @author tryu
 * @date 2022-12-12 14:29
 */
public class Nails {

    private Muscle muscle;

    private String nailsName;

    public Nails(Muscle muscle, String nailsName) {
        this.muscle = muscle;
        this.nailsName = nailsName;
    }

    public Nails(String nailsName) {
        this.nailsName = nailsName;
    }

    public Muscle getMuscle() {
        return muscle;
    }

    public void setMuscle(Muscle muscle) {
        this.muscle = muscle;
    }

    public String getNailsName() {
        return nailsName;
    }

    public void setNailsName(String nailsName) {
        this.nailsName = nailsName;
    }

    @Override
    public String toString() {
        return "Nails{" +
                "muscle=" + muscle +
                ", nailsName='" + nailsName + '\'' +
                '}';
    }
}
