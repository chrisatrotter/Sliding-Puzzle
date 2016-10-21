package puzzle.searchalgorithm;

import puzzle.Node;
import puzzle.SlidingSolution;
import puzzle.TileBoard;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;

/**
 * Created by chrisat on 19.10.16.
 */
public abstract class Graph implements SearchAlgorithm {

    protected Queue<Node> fringe;
    protected HashSet<Node> closedList;

    public Graph() {
        fringe = createFringe();
        closedList = new HashSet<>();
    }

    protected abstract Queue<Node> createFringe();

    public int getNodeExpanded() {
        return closedList.size();
    }

    public SlidingSolution search(TileBoard board) {
        fringe.add(new Node(board.getInitialState(), null, null));

        while(!fringe.isEmpty()) {
            Node node = fringe.poll();

            if (board.getGoalState().equals(node.getState())) {
                return new SlidingSolution(node, getNodeExpanded());
            }

            closedList.add(node);
            List<Node> possibilities = node.possibleMoves();
            possibilities.stream()
                         .filter(n -> !closedList.contains(n) && !fringe.contains(n))
                         .forEach(n -> fringe.add(n));
            //System.exit(1);
        }
        return new SlidingSolution(null, getNodeExpanded());
    }

}
