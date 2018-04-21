package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startTime = System.currentTimeMillis();
        generate(rows, columns); // what we need to to with the maze ?
        long endtTime = System.currentTimeMillis();
        return(endtTime - startTime);
    }

    protected void setWalls(Maze maze,int rows,int columns){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++)
                maze.setValueAt(i,j,1);
        }
    }

    protected Maze generateMaze (int rows, int columns)
    {
        if (rows < 1 || columns < 1)
            throw new RuntimeException("not valid size");
        Maze maze = new Maze(rows,columns);
        Position startPosition;
        startPosition = setRandomPosition(rows,columns);
        Position goalPosition = new Position(rows - startPosition.getRowIndex() - 1, columns - startPosition.getColumnIndex() - 1);
        maze.setStartPosition(startPosition);
        maze.setGoalPosition(goalPosition);
        return maze;
    }

    protected Position setRandomPosition(int rows, int columns){
        double rowOrColumn = Math.random();
        double startOrEnd = Math.random();
        int row=0;
        int column=0;
        if (rowOrColumn < 0.5  && startOrEnd < 0.5)
        {
            row = 0;
            column = (int) (Math.random()*columns);
        }
        if (rowOrColumn < 0.5  && startOrEnd >= 0.5)
        {
            row = rows - 1;
            column = (int) (Math.random()*columns);
        }
        if (rowOrColumn >= 0.5  && startOrEnd < 0.5)
        {
            row = (int) (Math.random()*rows);
            column = 0;
        }
        if (rowOrColumn >= 0.5  && startOrEnd >= 0.5)
        {
            row = (int) (Math.random()*rows);
            column = columns -1;
        }
        return (new Position(row,column));
    }


}
