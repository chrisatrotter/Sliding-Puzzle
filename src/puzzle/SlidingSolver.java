package puzzle;

import puzzle.searchalgorithm.SearchAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by chrisat on 17.10.16.
 */
public class SlidingSolver {
	private TileBoard tileBoard;
	private SearchAlgorithm algorithm;

	/*
	 * Constructs a SlidingSolver with the given input board.
	 */
	public SlidingSolver(TileBoard board, SearchAlgorithm algorithm) throws Exception {
		this.tileBoard = board;
		this.algorithm = algorithm;
		validateBoard(board);
	}

	public SlidingSolution solve() {
		return algorithm.search(getTileBoard());
	}

	public TileBoard getTileBoard() {
		return tileBoard;
	}

	/**
     * Validate the given tile board.
     * @param board - initial tile board.
     * @throws Exception - No possible solution for the tile board.
     */
	public void validateBoard(TileBoard board) throws Exception {
		if(!isSolvable(board.getInitialState().getBoard())) {
			throw new NoSuchAlgorithmException("None solvable puzzle.");
		}
	}

    /**
     * Check the board if it is solvable. Following conditions must be satisfied:
     * 1. If the grid width is odd, then the number of inversions in a solvable situation is even.
     * 2. If the grid width is even, and the blank is on an even row counting from the bottom (second-last, fourth-last etc),
     * then the number of inversions in a solvable situation is odd.
     * 3. If the grid width is even, and the blank is on an odd row counting from the bottom (last, third-last, fifth-last etc)
     * then the number of inversions in a solvable situation is even.
     * @param board - the initial board to verify if the puzzle is solvable.
     * @return True if the board is solvable, else false.
     */
    public boolean isSolvable(int[][] board) {
		int boardSize = this.tileBoard.getBoardSize();
        int inversion = 0; // count inversion.
        int blankRow = 0; // the row with the blank tile
        final int[] puzzle = Stream.of(board)
                             .flatMapToInt(IntStream::of)
                             .toArray();

		int row = 0; // count the rows bottom-up.
        for (int i = puzzle.length-1; i >= 0; i--) {
			if((i+1) % boardSize == 0)
				row++;

			if (puzzle[i] == 0) { // the row of the blank tile
				blankRow = row;
				break;
			}
		}

        for (int i = 0; i < puzzle.length; i++) {
            int finalI = i;
            inversion += (int) IntStream.range(i + 1, puzzle.length)
                                    .filter(j -> puzzle[finalI] > puzzle[j] && puzzle[j] != 0)
                                    .count();
        }

        //printable(boardSize, blankRow, inversion, puzzle);

		if (boardSize % 2 == 0) { // even grid
            if (blankRow % 2 == 0) // blank on even row; odd-parity.
                return inversion % 2 != 0;
            return inversion % 2 == 0; // blank on odd row; even-parity.
        }
        return inversion % 2 == 0; // odd grid; even-parity.
    }

    public void printable(int boardSize, int blankRow, int inversion, int[] puzzle) {
        System.out.println("\nBoardsize: " + boardSize);
		System.out.println("blankRow: " + blankRow);
		System.out.println("parity(inversion): " + inversion);
		System.out.print("Puzzle: ");
		Arrays.stream(puzzle).forEach(i -> System.out.print(i + ", "));
		System.out.println();
	}

}
