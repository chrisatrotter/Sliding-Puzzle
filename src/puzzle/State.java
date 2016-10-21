package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chrisat on 18.10.16.
 */
public class State {
    private final int boardSize;
    private int blankRow;
    private int blankColumn;
    private int[][] board;

    public State(int[][] board, int boardSize) {
        this.board = board;
        this.boardSize = boardSize;
        findZero(board);
    }

    public State(State state) {
        this.boardSize = state.getBoardSize();
        this.blankRow = state.getBlankRow();
        this.blankColumn = state.getBlankColumn();
        this.board = state.cloneboard(state.getBoard());
    }

    public void findZero(int[][] board) {
        for(int i = 0; i < boardSize; i++)
            for(int j = 0; j < boardSize; j++)
                if(board[i][j] == 0) {
                    setBlankRow(i);
                    setBlankColumn(j);
                }
    }

    public int[][] cloneboard(int[][] board) {
        int[][] clone = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++)
            for(int j = 0; j < boardSize; j++)
                clone[i][j] = board[i][j];
        return clone;
    }

    public void setBlankColumn(int blankColumn) {
        this.blankColumn = blankColumn;
    }

    public void setBlankRow(int blankRow) {
        this.blankRow = blankRow;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getBlankColumn() {
        return blankColumn;
    }

    public int getBlankRow() {
        return blankRow;
    }

    public void setValue(int row, int column, int value) {
        board[row][column] = value;
    }

    public int getValue(int row, int column) {
        return board[row][column];
    }

    public int[][] getBoard() {
        return board;
    }

    public void move(Move move) {
        if (move == Move.DOWN && blankRow != boardSize - 1) {
            board[blankRow][blankColumn] = board[blankRow+1][blankColumn];
            board[blankRow+1][blankColumn] = 0;
            blankRow++;
        }
        if (move == Move.RIGHT && blankColumn != boardSize - 1) {
            board[blankRow][blankColumn] = board[blankRow][blankColumn+1];
            board[blankRow][blankColumn+1] = 0;
            blankColumn++;
        }
        if (move == Move.UP && blankRow != 0) {
            board[blankRow][blankColumn] = board[blankRow-1][blankColumn];
            board[blankRow-1][blankColumn] = 0;
            blankRow--;
        }
        if (move == Move.LEFT && blankColumn != 0) {
            board[blankRow][blankColumn] = board[blankRow][blankColumn-1];
            board[blankRow][blankColumn-1] = 0;
            blankColumn--;
        }
    }

    public List<Move> getAdmissibleMoves() {
        List<Move> admissibleMoves = new ArrayList<>();
        admissibleMoves.add(Move.DOWN);
        admissibleMoves.add(Move.UP);
        admissibleMoves.add(Move.LEFT);
        admissibleMoves.add(Move.RIGHT);
        if (blankColumn == 0)
            admissibleMoves.remove(Move.LEFT);
        if (blankColumn == boardSize - 1)
            admissibleMoves.remove(Move.RIGHT);
        if (blankRow == 0)
            admissibleMoves.remove(Move.UP);
        if (blankRow == boardSize - 1)
            admissibleMoves.remove(Move.DOWN);
        return admissibleMoves;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof State))
            return false;
        State state = (State) obj;
        int[][] stateboard = state.getBoard();
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(stateboard[i][j] != this.board[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board)
                .replace("[", "")
                .replace("]", "");
    }
}
