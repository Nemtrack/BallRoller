package boardgame.model;

/**
 * Represents the four main directions.
 */
public enum Direction {

    /**
     * Represents the direction "UP".
     */
    UP(-1, 0),

    /**
     * Represents the direction "RIGHT".
     */
    RIGHT(0, 1),

    /**
     * Represents the direction "DOWN".
     */
    DOWN(1, 0),

    /**
     * Represents the direction "LEFT".
     */
    LEFT(0, -1);

    private final int rowChange;
    private final int colChange;

    Direction(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * @return the change in the row coordinate when moving to the direction
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * @return the change in the column coordinate when moving to the direction
     */
    public int getColChange() {
        return colChange;
    }
}
