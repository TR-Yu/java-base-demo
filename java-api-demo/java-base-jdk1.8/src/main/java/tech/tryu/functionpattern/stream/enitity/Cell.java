package tech.tryu.functionpattern.stream.enitity;

/**
 * 细胞
 *
 * @author tryu
 * @date 2022-12-12 14:33
 */
public class Cell {

    /**
     * default value
     */
    private String cellName = "lymphocytes";

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cellName='" + cellName + '\'' +
                '}';
    }
}
