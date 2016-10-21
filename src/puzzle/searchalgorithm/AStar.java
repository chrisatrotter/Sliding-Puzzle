package puzzle.searchalgorithm;

import puzzle.Node;
import puzzle.heuristic.Heuristic;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by chrisat on 19.10.16.
 */
public class AStar extends Graph implements HeuristicSearch {

    private Heuristic heuristic;

    public AStar(Heuristic heuristic) {
        setHeuristic(heuristic);
    }

    @Override
    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public Heuristic getHeuristic() {
        return heuristic;
    }

    @Override
    protected Queue<Node> createFringe() {
        return new PriorityQueue<> ((o1, o2) -> {
            o1.setH(getHeuristic().heuristic(o1));
            o2.setH(getHeuristic().heuristic(o2));
            o1.setF(o1.getC() + o1.getH());
            o2.setF(o2.getC() + o2.getH());

            if (o1.getF() < o2.getF())
                return -1;
            if (o1.getF() > o2.getF())
                return 1;
            return 0;
        });
    }
}
