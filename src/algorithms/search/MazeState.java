package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {

    private Position position;

    public MazeState(String state,Position position,AState cameFrom,double cost)
    {
        super(state,cameFrom,cost);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    @Override
    public String toString() {
        return position.toString();
    }
}
