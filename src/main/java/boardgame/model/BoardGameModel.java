package boardgame.model;

import java.util.ArrayList;

/**
 * The model of the boardgame.
 */
public class BoardGameModel {

    /**
     * The size of the board.
     */
    public static final int BOARD_SIZE = 7;

    private static int steps = 0;

    private static final Position goal = new Position(5, 2);

    private static final Position startingBallPosition = new Position(1, 4);

    private static final Position currentBallPosition = new Position(startingBallPosition.getRow(), startingBallPosition.getCol());

    /**
     * Initialization of the board.
     */
    public static final Square[][] board = new Square[BOARD_SIZE][BOARD_SIZE];

    private static final ArrayList<Position> hasUpperWalls = new ArrayList<>();

    private void setHasUpperWalls(){
        hasUpperWalls.add(new Position(1, 2));
        hasUpperWalls.add(new Position(1, 6));
        hasUpperWalls.add(new Position(3, 1));
        hasUpperWalls.add(new Position(4, 3));
        hasUpperWalls.add(new Position(4, 6));
        hasUpperWalls.add(new Position(5, 0));
        hasUpperWalls.add(new Position(5, 4));
        hasUpperWalls.add(new Position(6, 2));
    }

    private static final ArrayList<Position> hasRightWalls = new ArrayList<>();

    private void setHasRightWalls(){
        hasRightWalls.add(new Position(0, 0));
        hasRightWalls.add(new Position(0, 3));
        hasRightWalls.add(new Position(2, 2));
        hasRightWalls.add(new Position(2, 5));
        hasRightWalls.add(new Position(3, 3));
        hasRightWalls.add(new Position(3, 4));
        hasRightWalls.add(new Position(5, 1));
        hasRightWalls.add(new Position(5, 2));
        hasRightWalls.add(new Position(6, 3));
        hasRightWalls.add(new Position(6, 5));
    }

    private static final ArrayList<Position> hasDownWalls = new ArrayList<>();

    private void setHasDownWalls(){
        hasDownWalls.add(new Position(0, 2));
        hasDownWalls.add(new Position(0, 6));
        hasDownWalls.add(new Position(2, 1));
        hasDownWalls.add(new Position(3, 3));
        hasDownWalls.add(new Position(3, 6));
        hasDownWalls.add(new Position(4, 0));
        hasDownWalls.add(new Position(4, 4));
        hasDownWalls.add(new Position(5, 2));
    }

    private static final ArrayList<Position> hasLeftWalls = new ArrayList<>();

    private void setHasLeftWalls(){
        hasLeftWalls.add(new Position(0, 1));
        hasLeftWalls.add(new Position(0, 4));
        hasLeftWalls.add(new Position(2, 3));
        hasLeftWalls.add(new Position(2, 6));
        hasLeftWalls.add(new Position(3, 4));
        hasLeftWalls.add(new Position(3, 5));
        hasLeftWalls.add(new Position(5, 2));
        hasLeftWalls.add(new Position(5, 3));
        hasLeftWalls.add(new Position(6, 4));
        hasLeftWalls.add(new Position(6, 6));
    }

    /**
     * Setting up the walls for the board.
     */
    public BoardGameModel() {
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Square();
            }
        }

        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][0].setHasWallLeft(true);
                board[i][6].setHasWallRight(true);
                board[0][j].setHasWallUp(true);
                board[6][j].setHasWallDown(true);
            }
        }

        setHasUpperWalls();
        for (Position hasUpperWall : hasUpperWalls) {
            board[hasUpperWall.getRow()][hasUpperWall.getCol()].setHasWallUp(true);
        }
        setHasRightWalls();
        for (Position hasRightWall : hasRightWalls) {
            board[hasRightWall.getRow()][hasRightWall.getCol()].setHasWallRight(true);
        }
        setHasDownWalls();
        for (Position hasDownWall : hasDownWalls) {
            board[hasDownWall.getRow()][hasDownWall.getCol()].setHasWallDown(true);
        }
        setHasLeftWalls();
        for (Position hasLeftWall : hasLeftWalls) {
            board[hasLeftWall.getRow()][hasLeftWall.getCol()].setHasWallLeft(true);
        }
    }

    /**
     * @param position given will be decided if it is on the board
     * @return true if the position given is on the board or false otherwise
     */
    public boolean isOnBoard(Position position) {
        return position.getRow() >= 0 && position.getRow() < BOARD_SIZE &&
                position.getCol() >= 0 && position.getCol() < BOARD_SIZE;
    }

    /**
     * @return true if the Ball's current position equals the goal
     */
    public boolean isGameOver(){
        return currentBallPosition.equals(goal);
    }

    /**
     * Restarts the game by setting the current position to the starting position,
     * while setting the used steps to 0.
     */
    public void restartGame(){
        resetPosition();
        resetSteps();
    }

    /**
     * Resets the current position to the original starting position.
     */
    public void resetPosition(){
        currentBallPosition.setRow(startingBallPosition.getRow());
        currentBallPosition.setCol(startingBallPosition.getCol());
    }

    private void resetSteps(){ BoardGameModel.steps = 0; }

    /**
     * Will move the Ball in the direction given if it can.
     * @param direction the direction which the Ball will move in
     */
    public void move(Direction direction){
        if (canMove(direction))
            while (!isGameOver() && isOnBoard(currentBallPosition) && canMove(direction)) {
                currentBallPosition.setRow(currentBallPosition.getRow() + direction.getRowChange());
                currentBallPosition.setCol(currentBallPosition.getCol() + direction.getColChange());
            }
        incrementSteps();
    }

    /**
     * @param direction a direction which the function will check if it is valid
     * @return true if the given direction is valid
     */
    public boolean canMove(Direction direction) {
        return switch (direction) {
            case UP -> canMoveUp();
            case RIGHT -> canMoveRight();
            case DOWN -> canMoveDown();
            case LEFT -> canMoveLeft();
        };
    }

    private boolean canMoveUp(){
        return !board[currentBallPosition.getRow()][currentBallPosition.getCol()].isHasWallUp();
    }

    private boolean canMoveRight(){
        return !board[currentBallPosition.getRow()][currentBallPosition.getCol()].isHasWallRight();
    }

    private boolean canMoveDown(){
        return !board[currentBallPosition.getRow()][currentBallPosition.getCol()].isHasWallDown();
    }

    private boolean canMoveLeft(){
        return !board[currentBallPosition.getRow()][currentBallPosition.getCol()].isHasWallLeft();
    }

    private void incrementSteps(){
        steps++;
    }

    /**
     * @return the steps
     */
    public int getSteps(){
        return steps;
    }

    /**
     * @return the goal object
     */
    public Position getGoal(){
        return goal;
    }

    /**
     * @return the current Ball position
     */
    public Position getCurrentBallPosition(){
        return currentBallPosition;
    }

    /**
     * @return the starting ball position
     */
    public Position getStartingBallPosition(){
        return startingBallPosition;
    }
}
