package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = generateMaze(rows , columns);
        setWalls(maze,rows,columns);
        setRandomSolution(maze);
        return maze;
    }

    private void setRandomSolution(Maze maze){
        boolean [][]visitedArray = new boolean[maze.getNumOfRows()][maze.getNumOfColumns()];
        setVisitedArray(visitedArray,maze.getNumOfRows(),maze.getNumOfColumns());
        Stack<Position>stackOfPosition = new Stack<>();
        stackOfPosition.push(maze.getStartPosition());
        maze.setValueAt(maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex(),0);
        visitedArray[maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()]=true;
        maze.setValueAt(maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex(),0);
        visitedArray[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()]=true;
        setRandomSolution2(maze,visitedArray,stackOfPosition);
    }

    private void setRandomSolution2(Maze maze,boolean[][]visitedArray,Stack<Position>stackOfPosition)
    {
        boolean []goalFound = {false};
        boolean []keepOnPeeking = {true};
        while (!stackOfPosition.isEmpty())
        {
            List<Integer> integerList = new ArrayList<>();
            for (int i=0; i<4; i++) integerList.add(i);
            Position position=null;
            if (!keepOnPeeking[0]){stackOfPosition.pop();}
            if (!stackOfPosition.isEmpty()) {
                position=stackOfPosition.peek();
                if (position.equals(maze.getGoalPosition())) {
                    stackOfPosition.pop();
                    continue;
                }
                int currentRow = position.getRowIndex();
                int currentColumn = position.getColumnIndex();
                visitedArray[currentRow][currentColumn]=true;
                choosingNextCell(maze,visitedArray,currentRow,currentColumn,stackOfPosition,integerList,goalFound,keepOnPeeking);
            }
        }
        if (!goalFound[0]) setNewGoal(maze);
    }

    private void setNewGoal(Maze maze){
        maze.setValueAt(maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex(),1);
        int newColumn=0;
        int newRow=0;
        if (maze.getGoalPosition().getRowIndex()==0 || maze.getGoalPosition().getRowIndex() == maze.getNumOfRows()-1){
            if (maze.getGoalPosition().getColumnIndex()>maze.getNumOfColumns()/2) {
                newColumn = maze.getGoalPosition().getColumnIndex()+1;
                while  (newColumn<maze.getNumOfColumns()){
                    if (maze.getValueAt(maze.getGoalPosition().getRowIndex(),newColumn)==0){
                        maze.setGoalPosition(new Position(maze.getGoalPosition().getRowIndex(),newColumn));
                        return;
                    }
                    newColumn++;
                }
            }
            newColumn = newColumn = maze.getGoalPosition().getColumnIndex()-1;
            while  (newColumn>=0){
                if (maze.getValueAt(maze.getGoalPosition().getRowIndex(),newColumn)==0){
                    maze.setGoalPosition(new Position(maze.getGoalPosition().getRowIndex(),newColumn));
                    return;
                }
                newColumn--;
            }
        }
        if (maze.getGoalPosition().getRowIndex()>maze.getNumOfRows()/2) {
            newRow = maze.getGoalPosition().getRowIndex()+1;
            while  (newRow<maze.getNumOfRows()){
                if (maze.getValueAt(newRow,maze.getGoalPosition().getColumnIndex())==0){
                    maze.setGoalPosition(new Position(newRow,maze.getGoalPosition().getColumnIndex()));
                    return;
                }
                newRow++;
            }
        }
        newRow = maze.getGoalPosition().getRowIndex()-1;
        while  (newRow>=0){
            if (maze.getValueAt(newRow,maze.getGoalPosition().getColumnIndex())==0 ){
                maze.setGoalPosition(new Position(newRow,maze.getGoalPosition().getColumnIndex()));
                return;
            }
            newRow--;
        }
    }
    private void choosingNextCell(Maze maze,boolean[][]visitedArray,int currentRow, int currentColumn, Stack<Position>stackOfPosition, List<Integer>integerList,boolean[]goalFound, boolean[]keepOnPeeking ){
        int directionsSoFar = 0;
        boolean foundDirection=false;
        while (directionsSoFar<4 && !foundDirection)
        {
            int randomDirection = (int) (Math.random()*(4-directionsSoFar));
            randomDirection = integerList.remove(randomDirection);
            directionsSoFar++;
            if (randomDirection==0 && possibleWay("UP",currentRow - 1, currentColumn, visitedArray,maze.getNumOfRows(),maze.getNumOfColumns(),maze,goalFound))
            {
                foundDirection = true;
                keepOnPeeking[0]=true;
                visitedArray [currentRow-1] [currentColumn] = true;
                maze.setValueAt(currentRow-1,currentColumn,0);
                stackOfPosition.push(new Position(currentRow-1,currentColumn));
                return;
            }
            if (randomDirection==1 && possibleWay("RIGHT",currentRow, currentColumn+1, visitedArray,maze.getNumOfRows(),maze.getNumOfColumns(),maze,goalFound ))
            {
                foundDirection = true;
                keepOnPeeking[0]=true;
                visitedArray [currentRow] [currentColumn+1] = true;
                maze.setValueAt(currentRow,currentColumn+1,0);
                stackOfPosition.push(new Position(currentRow,currentColumn+1));
                return;
            }
            if (randomDirection==2 && possibleWay("DOWN",currentRow+1, currentColumn, visitedArray,maze.getNumOfRows(),maze.getNumOfColumns(),maze,goalFound ))
            {
                foundDirection = true;
                keepOnPeeking[0]=true;
                visitedArray [currentRow+1] [currentColumn] = true;
                maze.setValueAt(currentRow+1,currentColumn,0);
                stackOfPosition.push(new Position(currentRow+1,currentColumn));
                return;
            }
            if (randomDirection==3 && possibleWay("LEFT",currentRow , currentColumn-1, visitedArray,maze.getNumOfRows(),maze.getNumOfColumns(),maze, goalFound ))
            {
                foundDirection = true;
                keepOnPeeking[0]=true;
                visitedArray [currentRow] [currentColumn-1] = true;
                maze.setValueAt(currentRow,currentColumn-1,0);
                stackOfPosition.push(new Position(currentRow,currentColumn-1));
                return;
            }
        }
        keepOnPeeking[0]=false;
    }
    private boolean possibleWay(String direction,int wantedRow, int wantedColumn, boolean [][] visitedArray, int rows, int columns, Maze maze, boolean []goalFound){
        if (!maze.isValidLocation(wantedRow,wantedColumn) || visitedArray[wantedRow][wantedColumn])return false;
        boolean otherGoalFound = false;
        if (maze.isValidLocation(wantedRow +1, wantedColumn) && !direction.equals("UP") && visitedArray[wantedRow+1][wantedColumn] ){
            if (maze.getGoalPosition().equals(new Position(wantedRow+1,wantedColumn)) && !goalFound[0]) {otherGoalFound=true;}
            else return false;
        }
        if (maze.isValidLocation(wantedRow -1, wantedColumn) && !direction.equals("DOWN") && visitedArray[wantedRow-1][wantedColumn]) {
            if (maze.getGoalPosition().equals(new Position(wantedRow-1,wantedColumn)) && !goalFound[0]) {otherGoalFound= true;}
            else return false;
        }
        if (maze.isValidLocation(wantedRow , wantedColumn+1) && !direction.equals("LEFT") && visitedArray[wantedRow][wantedColumn+1]) {
            if (maze.getGoalPosition().equals(new Position(wantedRow,wantedColumn+1)) && !goalFound[0]) {otherGoalFound=true;}
            else return false;
        }
        if (maze.isValidLocation(wantedRow , wantedColumn-1) && !direction.equals("RIGHT") && visitedArray[wantedRow][wantedColumn-1]) {
            if (maze.getGoalPosition().equals(new Position(wantedRow,wantedColumn-1)) && !goalFound[0]) {otherGoalFound=true;}
            else return false;
        }
        if (otherGoalFound) goalFound[0]=true;
        return true;
    }


    private void setVisitedArray(boolean [][]visitedArray,int rows, int columns)
    {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++)
                visitedArray[i][j]=false;
        }
    }

}
