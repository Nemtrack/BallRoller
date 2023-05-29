package boardgame.controller;

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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller representing the {@link BoardGameModel}.
 */
public class BoardGameController {

    @FXML
    private GridPane gridPane;

    final private BoardGameModel model = new BoardGameModel();

    private String name;

    @FXML
    private void initialize() {
        populateGrid();
        registerKeyEventHandler();
        draw();
        Logger.info("Game Initialized!");
    }

    private void populateGrid(){
        Label label = new Label("CÉL");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        for (var i = 0; i < gridPane.getRowCount(); i++) {
            for (var j = 0; j < gridPane.getColumnCount(); j++) {
                var square = createSquare();
                gridPane.add(square, j, i);
                if (i == model.getGoal().getRow() && j == model.getGoal().getCol())
                    square.getChildren().add(label);
            }
        }
        Logger.info("Grid Populated!");
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setAlignment(Pos.CENTER);
        return square;
    }

    private void registerKeyEventHandler() {
        Platform.runLater(() -> gridPane.getScene().setOnKeyPressed(this::handleKeyPress));
    }

    private void draw() {
        removeBall();

        Position ballPosition = model.getCurrentBallPosition();
        Circle ball = createBall();
        gridPane.add(ball, ballPosition.getCol(), ballPosition.getRow());

        for (int y = 0; y < BoardGameModel.BOARD_SIZE; y++) {
            for (int x = 0; x < BoardGameModel.BOARD_SIZE; x++) {
                Square square = BoardGameModel.board[y][x];
                String borderstyle = "-fx-border-color: black; -fx-border-width:";
                borderstyle += square.isHasWallUp() ? " 5px" : " 1px";
                borderstyle += square.isHasWallRight() ? " 5px" : " 1px";
                borderstyle += square.isHasWallDown() ? " 5px" : " 1px";
                borderstyle += square.isHasWallLeft() ? " 5px;" : " 1px;";
                gridPane.getChildren().get(y * BoardGameModel.BOARD_SIZE + x).setStyle(
                        borderstyle);
            }
        }
        if (model.isGameOver()){
            loadEndScreen();
        }
    }

    private void loadEndScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/endScene.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }catch (IOException e){Logger.error(e.getLocalizedMessage());}
        EndGameController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        controller.setName(name);
        controller.setSteps(model.getSteps());
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        controller.setTime(formatter.format(now));
        Logger.info("Passing name: {}, victory date: {}, steps: {}, to next scene", controller.getName(),controller.getTime(),controller.getSteps());
        controller.showLabels();
        controller.showTable();
        Stage stage = (Stage) this.gridPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Logger.info("EndScreen drawn!");
    }

    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
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
                Logger.info("Game restarted!");
                Logger.info("Steps reseted!");
            }
        }
        draw();
    }

    private void performMove(Direction direction) {
        if (model.canMove(direction)) {
            Logger.info("Moving {}", direction);
            model.move(direction);
            Logger.trace("New state: {}", model.getCurrentBallPosition().toString());
            model.incrementSteps();
            Logger.info("Steps: {}",model.getSteps());
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
        return new Circle(50, Color.BLUE);
    }

    /**
     * Setter for the name variable.
     * @param name will be the new name set for the name of the player
     */
    public void setName(String name){
        this.name = name;
        Logger.info("name was set to: {}",name);
    }
}

