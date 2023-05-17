package boardgame.model;

public class Square {

    private boolean hasWallUp;
    private boolean hasWallRight;
    private boolean hasWallDown;
    private boolean hasWallLeft;

    public void setHasWallUp(boolean hasWallUp) {
        this.hasWallUp = hasWallUp;
    }

    public void setHasWallRight(boolean hasWallRight) {
        this.hasWallRight = hasWallRight;
    }

    public void setHasWallDown(boolean hasWallDown) {
        this.hasWallDown = hasWallDown;
    }

    public void setHasWallLeft(boolean hasWallLeft) {
        this.hasWallLeft = hasWallLeft;
    }

    public boolean isHasWallUp() {
        return hasWallUp;
    }

    public boolean isHasWallRight() {
        return hasWallRight;
    }

    public boolean isHasWallDown() {
        return hasWallDown;
    }

    public boolean isHasWallLeft() {
        return hasWallLeft;
    }


}
