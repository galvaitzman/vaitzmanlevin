package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
       Maze maze = generateMaze(rows , columns);
       setRandomSolution(maze,rows,columns, maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex());
       return maze;
    }
    private void setRandomSolution(Maze maze,int rows,int columns, int rowStart, int columnStart ){
        setWalls(maze,rows,columns);
        setRandomSolution2(maze,rowStart,columnStart,maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex());
        setRandomWalls(maze);
    }
    private void setRandomSolution2(Maze maze,int currentRow,int currentColumn, int goalRow, int goalColumn){
        boolean solutionFound = false;
        while (!solutionFound) {
            int random = (int) (Math.random() * 2);
            if (currentRow == goalRow && currentColumn == goalColumn) solutionFound = true;
            else if (currentRow == goalRow)  {
                if (currentColumn > goalColumn) currentColumn--;
                else currentColumn++;
            }
            else if (currentRow > goalRow)  {
                if (currentColumn == goalColumn) currentRow--;
                else if (currentColumn > goalColumn)
                {
                    currentRow = currentRow + setNewPosition(-1,0,random);
                    currentColumn = currentColumn + setNewPosition (0,-1,random);
                }
                else
                {
                    currentRow = currentRow + setNewPosition(-1,0,random);
                    currentColumn = currentColumn + setNewPosition (0,1,random);
                }
            }
            else if (currentRow < goalRow)  {
                if (currentColumn == goalColumn) currentRow++;
                else if (currentColumn > goalColumn)
                {
                    currentRow = currentRow + setNewPosition(1,0,random);
                    currentColumn = currentColumn + setNewPosition (0,-1,random);
                }
                else
                {
                    currentRow = currentRow + setNewPosition(1,0,random);
                    currentColumn = currentColumn + setNewPosition (0,1,random);
                }
            }
            maze.setValueAt(currentRow, currentColumn, 0);
        }
    }
    private  void setRandomWalls(Maze maze)
    {
        for (int i = 0 ; i < maze.getNumOfRows() ; i ++)
            for( int j =0; j < maze.getNumOfColumns() ; j++)
            {
               double random = Math.random();
               if(random >= 0.5)
                   maze.setValueAt(i, j, 0);
            }
    }

    private int setNewPosition(int row,int column,int random)
    {
        if (random == 0) return row;
        return column;
    }

}
