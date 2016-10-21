package puzzle;

import puzzle.heuristic.Heuristic;
import puzzle.heuristic.ManhattanDistance;
import puzzle.searchalgorithm.AStar;
import puzzle.searchalgorithm.SearchAlgorithm;

import java.io.File;
import java.util.Scanner;

/**
 * Created by chrisat on 17.10.16.
 */
public class SlidingSolverDriver {
	public static void main(String[] args) throws Exception {
		if(args.length != 1)
			throw new IllegalArgumentException("Not correct type of arguments");

		TileBoard initialBoard = new TileBoard(readFile(args[0]));
		Heuristic heuristic = new ManhattanDistance(initialBoard.getBoardSize());
		SearchAlgorithm algorithm = new AStar(heuristic);

		System.out.println("Initial board: ");
		System.out.println(initialBoard.getInitialState().toString());

		System.out.println("\nGoal board: ");
		System.out.println(initialBoard.getGoalState().toString());


		SlidingSolution solution = new SlidingSolver(initialBoard, algorithm).solve();
		solution.printSolution();
	}

	/**
	 * Check initial state of the file. If it is empty at start, but does not validate whole file.
	 * This could(should) be done in retrospect.
	 * Note: Only considers NxN sized boards and not NxM.
	 * @return a puzzle board from the piped input.
	 */
	public static int[][] readFile(String file) throws Exception{
		Scanner in = new Scanner(new File(file));

		if(!in.hasNext())
			throw new NoSuchFieldError("Empty file is found. Not valid preferred format.");

		int size = in.nextInt();
		in.nextLine();
		int[][] board = new int[size][size];

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j] = in.nextInt();
			}
			in.nextLine();
		}
		return board;
	}
}








