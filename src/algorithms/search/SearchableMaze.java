package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze maze;
    private MazeState startState;
    private MazeState goalState;
    private boolean[][]visitedArray;

    public SearchableMaze(Maze maze)
    {
        this.maze = maze;
        startState = new MazeState("START", maze.getStartPosition(),null,0);
        goalState = new MazeState("GOAL", maze.getGoalPosition(),null,0);
        visitedArray = new boolean [maze.getNumOfRows()][maze.getNumOfColumns()];
        setVisitedArray(maze.getNumOfRows(),maze.getNumOfColumns());
    }
    @Override
    public AState getStartState() {
       return this.startState;
    }

    @Override
    public AState getGoalState() {
        return this.goalState;
    }

    private void setVisitedArray(int rows, int columns)
    {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++)
                visitedArray[i][j] = false;
        }
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState as) {
        MazeState currentState = (MazeState)as;
        int currentRow = currentState.getPosition().getRowIndex();
        int currentColumn =  currentState.getPosition().getColumnIndex();
        visitedArray[currentRow][currentColumn] = true;
        ArrayList<AState> allSuccessors = new ArrayList<>();
        addAllPossibleSuccessors(currentState,currentRow,currentColumn,allSuccessors);
        return allSuccessors;
    }

    private void addAllPossibleSuccessors(MazeState currentState,int currentRow, int currentColumn,ArrayList<AState>allSuccessors) {
        boolean validUp = maze.isValidLocation(currentRow - 1,currentColumn);
        boolean possibleUp = validUp && maze.getValueAt(currentRow - 1,currentColumn) == 0;
        boolean validRight = maze.isValidLocation(currentRow,currentColumn+1);
        boolean possibleRight = validRight && maze.getValueAt(currentRow,currentColumn + 1) == 0;
        boolean validDown = maze.isValidLocation(currentRow + 1,currentColumn);
        boolean possibleDown = validDown && maze.getValueAt(currentRow + 1,currentColumn) == 0;
        boolean validLeft = maze.isValidLocation(currentRow,currentColumn - 1);
        boolean possibleLeft = validLeft && maze.getValueAt(currentRow,currentColumn - 1) == 0;
        addPossibleRegularSuccessor(currentState, possibleUp,allSuccessors,currentRow - 1, currentColumn);
        addPossibleSlantSuccessor  (currentState, validUp&&validRight,possibleUp || possibleRight,allSuccessors,currentRow - 1, currentColumn + 1);
        addPossibleRegularSuccessor(currentState, possibleRight,allSuccessors,currentRow, currentColumn + 1);
        addPossibleSlantSuccessor  (currentState, validRight&&validDown, possibleRight || possibleDown,allSuccessors,currentRow + 1, currentColumn + 1);
        addPossibleRegularSuccessor(currentState, possibleDown,allSuccessors,currentRow + 1, currentColumn);
        addPossibleSlantSuccessor  (currentState,validDown&&validLeft, possibleDown || possibleLeft,allSuccessors,currentRow + 1, currentColumn - 1);
        addPossibleRegularSuccessor(currentState, possibleLeft,allSuccessors,currentRow, currentColumn - 1);
        addPossibleSlantSuccessor  (currentState,validLeft&&validUp, possibleLeft || possibleUp,allSuccessors,currentRow - 1, currentColumn - 1);
    }

    private boolean legalDirection (int row, int column, Maze maze){
        return (maze.isValidLocation(row,column) && maze.getValueAt(row,column) == 0);
    }

    private void addPossibleRegularSuccessor(MazeState currentState,boolean direction, ArrayList<AState>allSuccessors,int wantedRow, int wantedColumn){
        String state = setStateName(wantedRow,wantedColumn);
        if (direction && !visitedArray[wantedRow][wantedColumn]){
            allSuccessors.add(new MazeState(state,new Position(wantedRow,wantedColumn),currentState,10));
            visitedArray[wantedRow][wantedColumn] = true;
        }


    }

    private void addPossibleSlantSuccessor(MazeState currentState,boolean validation, boolean atLeastOneNeighbourIsZero, ArrayList<AState>allSuccessors,int wantedRow, int wantedColumn){
        String state = setStateName(wantedRow,wantedColumn);
        if (atLeastOneNeighbourIsZero && validation && !visitedArray[wantedRow][wantedColumn] && maze.getValueAt(wantedRow,wantedColumn)==0){
            allSuccessors.add(new MazeState(state, new Position(wantedRow,wantedColumn), currentState,15));
            visitedArray[wantedRow][wantedColumn] = true;
        }

    }

    private String setStateName(int wantedRow, int wantedColumn){
        if (goalState.getPosition().equals(new Position(wantedRow,wantedColumn)))
            return "GOAL";
        return "random";
    }


}
