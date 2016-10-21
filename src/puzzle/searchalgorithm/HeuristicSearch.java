package puzzle.searchalgorithm;

import puzzle.heuristic.Heuristic;

/**
 * Created by chrisat on 19.10.16.
 */
public interface HeuristicSearch {
    public void setHeuristic(Heuristic heuristic);
    public Heuristic getHeuristic();
}
