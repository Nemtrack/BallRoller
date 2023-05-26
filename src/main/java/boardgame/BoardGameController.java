package boardgame;

import boardgame.model.BoardGameModel;
import boardgame.model.Direction;
import boardgame.model.Position;
import boardgame.model.Square;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class BoardGameController {

    @FXML
    private GridPane gridPane;

    final private BoardGameModel model = new BoardGameModel();


    @FXML
    private void initialize() {
        populateGrid();
        registerKeyEventHandler();
        draw();
    }

    private void populateGrid(){
        for (var i = 0; i < gridPane.getRowCount(); i++) {
            for (var j = 0; j < gridPane.getColumnCount(); j++) {
                var square = createSquare(i, j);
                gridPane.add(square, j, i);
            }
        }
    }

    private StackPane createSquare(int row, int col) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setAlignment(Pos.CENTER);
        return square;
    }

    private void registerKeyEventHandler() {
        Platform.runLater(() -> gridPane.getScene().setOnKeyPressed(this::handleKeyPress));
    }

    public void draw() {
        removeBall();

        Position ballPosition = model.getCurrentBallPosition();
        Circle ball = createBall();
        gridPane.add(ball, ballPosition.getCol(), ballPosition.getRow());

        for (int y = 0; y < model.BOARD_SIZE; y++) {
            for (int x = 0; x < model.BOARD_SIZE; x++) {
                Square square = model.board[y][x];
                String borderstyle = "-fx-border-color: black; -fx-border-width:";
                borderstyle += square.isHasWallUp() ? " 5px" : " 1px";
                borderstyle += square.isHasWallRight() ? " 5px" : " 1px";
                borderstyle += square.isHasWallDown() ? " 5px" : " 1px";
                borderstyle += square.isHasWallLeft() ? " 5px;" : " 1px;";
                gridPane.getChildren().get(y * model.BOARD_SIZE + x).setStyle(
                        borderstyle);
            }
        }
        System.out.printf("Steps: %d", model.getSteps());

        if (model.isGameOver()){
            loadEndScreen();
        }
    }

    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        model.incrementSteps();
        switch (keyEvent.getCode()) {
            case UP -> {
                Logger.info("{} pressed", keyEvent.getCode());
                performMove(Direction.UP);
            }
            case RIGHT -> {
                Logger.info("{} pressed", keyEvent.getCode());
                performMove(Direction.RIGHT);
            }
            case DOWN -> {
                Logger.info("{} pressed", keyEvent.getCode());
                performMove(Direction.DOWN);
            }
            case LEFT -> {
                Logger.info("{} pressed", keyEvent.getCode());
                performMove(Direction.LEFT);
            }
            case R -> {
                model.restartGame();
                Logger.info("{} pressed", keyEvent.getCode());
                Logger.info("Game Restarted!");
            }
        }
        draw();
    }

    private void performMove(Direction direction) {
        if (model.canMove(direction)) {
            Logger.info("Moving {}", direction);
            model.move(direction);
            Logger.trace("New state: {}", model.getCurrentBallPosition().toString());
        } else {
            Logger.warn("Invalid move: {}", direction);
        }
    }

    private void removeBall() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Circle) {
                gridPane.getChildren().remove(node);
                break;
            }
        }
    }

    private Circle createBall() {
        Circle ball = new Circle(50, Color.BLUE);
        return ball;
    }
}

