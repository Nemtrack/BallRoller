package boardgame;

import boardgame.model.BoardGameModel;
import boardgame.model.Direction;
import boardgame.model.Position;
import boardgame.model.Square;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.tinylog.Logger;

public class BoardGameController {

    @FXML
    private GridPane gridPane;

    final private BoardGameModel model = new BoardGameModel();

    Position previousStep = new Position(model.getCurrentBallPosition().getRow(), model.getCurrentBallPosition().getCol());

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
        var piece = new Circle(50);
        return square;
    }

    private void registerKeyEventHandler() {
        Platform.runLater(() -> gridPane.getScene().setOnKeyPressed(this::handleKeyPress));
    }

    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP) {
            Logger.debug("UP pressed");
            performMove(Direction.UP);
        } else if (keyEvent.getCode() == KeyCode.RIGHT) {
            Logger.debug("RIGHT pressed");
            performMove(Direction.RIGHT);
        } else if (keyEvent.getCode() == KeyCode.DOWN) {
            Logger.debug("DOWN pressed");
            performMove(Direction.DOWN);
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            Logger.debug("LEFT pressed");
            performMove(Direction.LEFT);
        }
        draw();
    }

    private void performMove(Direction direction) {
        if (model.canMove(direction)) {
            Logger.info("Moving {}", direction);
            model.move(direction);
            Logger.trace("New state: {}", model);
        } else {
            Logger.warn("Invalid move: {}", direction);
        }
    }

    public void draw() {
        System.out.println(model.getCurrentBallPosition().toString());
        System.out.println(model.getCurrentBallPosition().getRow() * 7  + model.getCurrentBallPosition().getCol());
        for (int y = 0; y < model.BOARD_SIZE; y++) {
            for (int x = 0; x < model.BOARD_SIZE; x++) {
                Square square = model.board[y][x];
                var piece = new Circle(50);
                String borderstyle = "-fx-border-color: black; -fx-border-width:";
                borderstyle += square.isHasWallUp() ? " 5px" : " 1px";
                borderstyle += square.isHasWallRight() ? " 5px" : " 1px";
                borderstyle += square.isHasWallDown() ? " 5px" : " 1px";
                borderstyle += square.isHasWallLeft() ? " 5px;" : " 1px;";
                gridPane.getChildren().get(y * model.BOARD_SIZE + x).setStyle(
                            borderstyle);
            }
        }
//        Node circle = gridPane.getChildren().get(previousStep.getRow() * model.BOARD_SIZE + previousStep.getCol());
        gridPane.getChildren().remove(player);
        previousStep.setCol(model.getCurrentBallPosition().getCol());
        previousStep.setRow(model.getCurrentBallPosition().getRow());
        var player = new Circle(40);
        player.setFill(Color.BLUE);
        gridPane.add(player, model.getCurrentBallPosition().getCol(), model.getCurrentBallPosition().getRow());
//                    piece.setStyle("fx-background-color:blue;");
//                    gridPane.getChildren()
//                            .get(model.getCurrentBallPosition().getRow() + model.getCurrentBallPosition().getCol())
//                            .add(piece);
    }
}

