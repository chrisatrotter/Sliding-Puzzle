package puzzle;

/**
 * Created by chrisat on 17.10.16.
 */
public class TileBoard
{
	private State goalState;
    private State initialState;

    //Int size of the board
	private int boardSize;

	public TileBoard(int[][] board) {
        boardSize = board.length;
		this.initialState = new State(board, boardSize);
        this.goalState = new State(generateGoalBoard(board.length), boardSize);
	}

	public int[][] generateGoalBoard(int size) {
        int count = 1;
        int [][] goalBoard = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                goalBoard[i][j] = count++;
            }
        }
        goalBoard[size-1][size-1] = 0;
        return goalBoard;
    }

    public State getGoalState() {
        return goalState;
    }

    public State getInitialState() {
        return initialState;
    }

    public int getBoardSize() {
        return boardSize;
    }

}
