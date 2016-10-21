package puzzle;

/**
 * Created by chrisat on 18.10.16.
 */
public enum Move {
    LEFT("L"),
    RIGHT("R"),
    UP("U"),
    DOWN("D");

    private String move;

    Move(String move) {
        this.move = move;
    }

    public String getMove() {
        return move;
    }
}
