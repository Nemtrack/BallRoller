package boardgame.model;

/**
 * Represents a 2D position.
 */
public class Position {

    private int row;
    private int col;

    /**
     * Constructor of the Position
     * @param row representing the row of the Position
     * @param col representing the column of the Position
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @param newRow given will set the Position's row to this parameter
     */
    public void setRow(int newRow){
        this.row = newRow;
    }

    /**
     * @param newCol given will set the Position's column to this parameter
     */
    public void setCol(int newCol){
        this.col = newCol;
    }

    /**
     * @return the row of the Position
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column of the Position
     */
    public int getCol() {
        return col;
    }

    /**
     * @param obj the Position which will be compared to the Position
     * @return true if the given Position is the same as the Position
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return row == other.row && col == other.col;
    }

    /**
     * @return the Position as String
     */
    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
