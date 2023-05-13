package boardgame.model;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class BoardGameModel {

    public static final int BOARD_SIZE = 7;

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];

    public BoardGameModel(){
        for (var i = 0 ; i < BOARD_SIZE; i++){
            for (var j = 0; j < BOARD_SIZE; j++){
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.EMPTY);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new BoardGameModel();
        System.out.println(model);
    }
}
