import boardgame.model.BoardGameModel;
import boardgame.model.Direction;
import boardgame.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    BoardGameModel model = new BoardGameModel();
    Position position = new Position(0, 0);

    @BeforeEach
    public void setup(){
        model.resetPosition();
        position.setCol(0);
        position.setRow(0);
    }

    @Test
    void moveBallUp(){
        model.move(Direction.UP);
        assertEquals(new Position(model.getStartingBallPosition().getRow()-1, model.getStartingBallPosition().getCol()),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallUpBlocked(){
        model.move(Direction.UP);
        model.move(Direction.UP);
        assertEquals(new Position(model.getStartingBallPosition().getRow()-1, model.getStartingBallPosition().getCol()),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallRight(){
        model.move(Direction.RIGHT);
        assertEquals(new Position(model.getStartingBallPosition().getRow(), model.getStartingBallPosition().getCol()+2),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallRightBlocked(){
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        assertEquals(new Position(model.getStartingBallPosition().getRow(), model.getStartingBallPosition().getCol()+2),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallDown(){
        model.move(Direction.DOWN);
        assertEquals(new Position(model.getStartingBallPosition().getRow()+3, model.getStartingBallPosition().getCol()),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallDownBlocked(){
        model.move(Direction.DOWN);
        model.move(Direction.DOWN);
        assertEquals(new Position(model.getStartingBallPosition().getRow()+3, model.getStartingBallPosition().getCol()),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallLeft(){
        model.move(Direction.LEFT);
        assertEquals(new Position(model.getStartingBallPosition().getRow(), model.getStartingBallPosition().getCol()-4),
                model.getCurrentBallPosition());
    }

    @Test
    void moveBallLeftBlocked(){
        model.move(Direction.LEFT);
        assertEquals(new Position(model.getStartingBallPosition().getRow(), model.getStartingBallPosition().getCol()-4),
                model.getCurrentBallPosition());
    }

    @Test
    void gameFinish(){
        model.move(Direction.RIGHT);
        model.move(Direction.DOWN);
        model.move(Direction.LEFT);
        model.move(Direction.DOWN);
        model.move(Direction.LEFT);
        model.move(Direction.UP);
        model.move(Direction.LEFT);
        model.move(Direction.DOWN);
        model.move(Direction.LEFT);
        model.move(Direction.UP);
        model.move(Direction.RIGHT);
        model.move(Direction.UP);
        model.move(Direction.RIGHT);
        model.move(Direction.UP);
        model.move(Direction.LEFT);
        model.move(Direction.DOWN);
        model.move(Direction.RIGHT);
        model.move(Direction.DOWN);

        assertTrue(model.isGameOver());
    }

    @Test
    void notGameFinish(){
        model.move(Direction.RIGHT);
        model.move(Direction.DOWN);
        model.move(Direction.LEFT);
        model.move(Direction.DOWN);
        model.move(Direction.LEFT);

        assertFalse(model.isGameOver());
    }

    @Test
    void invalidPosition(){
        position.setRow(-1);
        assertFalse(model.isOnBoard(position));
    }

    @Test
    void validPosition(){
        position.setRow(4);
        position.setCol(4);
        assertTrue(model.isOnBoard(position));
    }

    @Test
    void restartCheck(){
        model.restartGame();
        assertEquals(model.getStartingBallPosition(), model.getCurrentBallPosition());
        assertEquals(0, model.getSteps());
    }

    @Test
    void equalsCheck(){
        boolean equality = position.equals(new Position(0, 0));
        assertTrue(equality);
    }
}
