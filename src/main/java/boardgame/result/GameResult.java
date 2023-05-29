package boardgame.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * A class representing the result of a game played by a specific player.
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class GameResult {
    private String name;
    private String date;
    private int steps;

    /**
     * @return the name of the player, the steps it took to finish the game and the date of completion
     */
    @Override
    public String toString() {
        return name+": steps: "+steps+", date of victory: "+date;
    }
}
