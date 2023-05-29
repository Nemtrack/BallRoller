package boardgame.model;

/**
 * Represents a square on the board.
 */
public class Square {

    private boolean hasWallUp;
    private boolean hasWallRight;
    private boolean hasWallDown;
    private boolean hasWallLeft;

    /**
     * @param hasWallUp will be set true
     */
    public void setHasWallUp(boolean hasWallUp) {
        this.hasWallUp = hasWallUp;
    }

    /**
     * @param hasWallRight will be set true
     */
    public void setHasWallRight(boolean hasWallRight) {
        this.hasWallRight = hasWallRight;
    }

    /**
     * @param hasWallDown will be set true
     */
    public void setHasWallDown(boolean hasWallDown) {
        this.hasWallDown = hasWallDown;
    }

    /**
     * @param hasWallLeft will be set true
     */
    public void setHasWallLeft(boolean hasWallLeft) {
        this.hasWallLeft = hasWallLeft;
    }

    /**
     * @return the hasWallUp boolean
     */
    public boolean isHasWallUp() {
        return hasWallUp;
    }

    /**
     * @return the hasWallRight boolean
     */
    public boolean isHasWallRight() {
        return hasWallRight;
    }

    /**
     * @return the hasWallDown boolean
     */
    public boolean isHasWallDown() {
        return hasWallDown;
    }

    /**
     * @return the hasWallLeft boolean
     */
    public boolean isHasWallLeft() {
        return hasWallLeft;
    }
}
