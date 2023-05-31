package boardgame.controller;

import boardgame.result.GameResult;
import boardgame.result.GameResultStorage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the EndGame Scene.
 */
public class EndGameController {

    @FXML
    private Label stepsLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private TextArea resultField;

    private final GameResultStorage gameResultStorage = new GameResultStorage();

    private String name;
    private int steps;
    private String time;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the steps
     */
    public int getSteps() {
        return steps;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param name given will set the name to the parameter
     */
    public void setName(String name) {
        this.name = name;
        Logger.info("name is set to {}", name);
    }

    /**
     * @param steps given will set the steps to the parameter
     */
    public void setSteps(int steps) {
        this.steps = steps;
        Logger.info("steps is set to {}", steps);
    }

    /**
     * @param time given will set the time to the parameter
     */
    public void setTime(String time) {
        this.time = time;
        Logger.info("victoryTime is set to {}", time);
    }

    /**
     * Showing the labels on the EndGame Scene while saving the player.
     */
    public void showLabels(){
        Logger.info("Showing labels on EndGameController");
        nameLabel.setText("CONGRATULATIONS, \""+name+"\" YOU WIN!!!");
        dateLabel.setText("Date: "+ time);
        stepsLabel.setText("Steps: "+steps);
        try{
            savePlayer();
        }catch (IOException e){Logger.error(e.getMessage());}
    }

    private void savePlayer() throws IOException {
        gameResultStorage.addOne(createGameResult());
        Logger.info("player saved");
    }

    /**
     * Generating the gameResultList object into a list and showing the top 10 previous results.
     */
    public void showTable(){
        List<GameResult> gameResultList = gameResultStorage.toList();
        resultField.setText("Previous results: \n");
        for(int i=0; i<Math.min(10, gameResultList.size()); i++){
            resultField.setText(resultField.getText() + gameResultList.get(i).toString()+"\n");
        }
    }

    private GameResult createGameResult() {
        return GameResult.builder()
                .name(this.name)
                .date(this.time)
                .steps(this.steps)
                .build();
    }

    @FXML
    private void quit(){
        Logger.info("Shutting down...");
        Platform.exit();
    }
}
