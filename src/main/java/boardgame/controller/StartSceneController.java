package boardgame.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * Controller of the Starting Scene
 */
public class StartSceneController {

    @FXML
    private TextField nameField;

    /**
     * Starting the game with logging the name given, setting it and setting the Title of the Stage
     */
    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gameStart.fxml"));
        Logger.info("Name entered: {}", nameField.getText());
        Parent root = fxmlLoader.load();
        BoardGameController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        controller.setName(nameField.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("BallRoller");
        stage.setScene(scene);
        stage.show();
    }
}
