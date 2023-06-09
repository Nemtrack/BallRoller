package boardgame.result;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.tinylog.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Used for storing {@link GameResult} entities.
 */
public class GameResultStorage{
    private final ObjectMapper oj = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private List<GameResult> gameResultStorage;

    /**
     * Creates the storage by reading the player.json file.
     */
    public GameResultStorage() {

        String absoluteFilePath = Path.of("player.json").toAbsolutePath().toString();

        try {
            gameResultStorage = oj.readValue(new FileReader(absoluteFilePath), new TypeReference<>() {
            });
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    /**
     * Adds one result to the storage and rewrites the json file.
     * @param element a result a player got
     */
    public void addOne(GameResult element){
        String absoluteFilePath = Path.of("player.json").toAbsolutePath().toString();

        try(var writer = new FileWriter(absoluteFilePath)){
            gameResultStorage.add(element);
            oj.writeValue(writer,gameResultStorage);
            Logger.info("element added");
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    /**
     * @return a list of game results of the storage
     */
    public List<GameResult> toList(){
        return new ArrayList<>(gameResultStorage)
                .stream()
                .sorted(Comparator.comparingInt(GameResult::getSteps))
                .toList();
    }
}