package puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisat on 18.10.16.
 */
public class Node {
    private State state;
    private Node parent;
    private Move move;
    private int f;
    private int c;
    private int h;

    public Node(State state, Node parent, Move move) {
        this.state = state;
        this.parent = parent;
        this.move = move;
        this.f = 0;
        this.c = 0;
        this.h = 0;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Move getMove() {
        return move;
    }

    public int getF() {
        return getC()+getH();
    }

    public int getC() {
        return this.c;
    }

    public int getH() {
        return this.h;
    }

    public void setF(int f) { this.f = f; }

    public void setC(int c) {
        this.c = c;
    }

    public void setH(int h) {
        this.h = h;
    }

    public List<Node> getPath() {
        List<Node> path = new ArrayList<>();
        Node node = this;
        while(node.getParent() != null) {
            path.add(node);
            node = node.getParent();
        }
        return path;
    }

    public List<Node> possibleMoves() {
        List<Node> nodes = new ArrayList<>();
        List<Move> moves  = state.getAdmissibleMoves();
        for(Move move : moves) {
            State copyState = new State(getState());
            copyState.move(move);
            Node node = new Node(copyState, this, move);
            node.setC(getC()+1);
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Node))
            return false;
        Node node = (Node) o;
        return node.getState().equals(this.state);
    }
}
