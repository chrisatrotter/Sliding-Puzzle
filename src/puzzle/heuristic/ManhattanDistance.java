package puzzle.heuristic;

import puzzle.Node;

/**
 * Created by chrisat on 18.10.16.
 */
public class ManhattanDistance implements Heuristic {
    private int boardSize;

    public ManhattanDistance(int boardSize) {
        this.boardSize = boardSize;
    }


    @Override
    public int heuristic(Node n) {
        int distance = 0;
        int[][] board = n.getState().getBoard();

        for (int x = 0; x < boardSize; x++) // x-dimension, traversing rows (i)
            for (int y = 0; y < boardSize; y++) { // y-dimension, traversing cols (j)
                int value = board[x][y]; // tiles array contains board elements
                if (value != 0) { // we don't compute MD for element 0
                    int targetX = (value - 1) / boardSize; // expected x-coordinate (row)
                    int targetY = (value - 1) % boardSize; // expected y-coordinate (col)
                    int dx = x - targetX; // x-distance to expected coordinate
                    int dy = y - targetY; // y-distance to expected coordinate
                    distance += Math.abs(dx) + Math.abs(dy);
                }
            }
        return distance;
    }
}
