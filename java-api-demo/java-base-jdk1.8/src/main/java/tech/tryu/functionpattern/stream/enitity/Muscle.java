package tech.tryu.functionpattern.stream.enitity;

/**
 * 肌肉
 *
 * @author tryu
 * @date 2022-12-12 14:32
 */
public class Muscle {
    private Cell cell;

    private String muscleName;

    public Muscle(Cell cell, String muscleName) {
        this.cell = cell;
        this.muscleName = muscleName;
    }

    public Muscle(String muscleName) {
        this.muscleName = muscleName;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    @Override
    public String toString() {
        return "Muscle{" +
                "cell=" + cell +
                ", muscleName='" + muscleName + '\'' +
                '}';
    }
}
