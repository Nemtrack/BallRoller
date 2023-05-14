package boardgame.model;

import javafx.beans.property.ReadOnlyObjectProperty;
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
        board[1][4] = new ReadOnlyObjectWrapper<Square>(Square.BALL);
    }

    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    public Square getSquare(int i, int j) {
        return board[i][j].get();
    }


    public void move(int i, int j) {
        board[i][j].set(
                switch (board[i][j].get()) {
                    case EMPTY -> Square.BALL;
                    case BALL -> Square.EMPTY;
                }
        );
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
