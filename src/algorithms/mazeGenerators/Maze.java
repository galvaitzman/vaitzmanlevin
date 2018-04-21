package algorithms.mazeGenerators;
public class Maze {
    private Position startPosition;
    private Position goalPosition;
    private int [][] mazeMatrix;

    public Maze(int rows, int columns) {
        mazeMatrix = new int [rows][columns];
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }
    public int getValueAt(int row, int column){
        if (row > mazeMatrix.length - 1 || column > mazeMatrix[0].length -1)
            throw new RuntimeException ("not valid position");
        return mazeMatrix[row][column];
    }
    public void setValueAt(int row, int column, int value){
        mazeMatrix[row][column] = value;
    }
    public int getNumOfRows(){
        return mazeMatrix.length;
    }
    public int getNumOfColumns(){
        return mazeMatrix[0].length;
    }
    public void print(){
        for( int i = 0 ; i < getNumOfRows() ; i ++ ) {
            for (int j = 0; j < getNumOfColumns(); j++)
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex())
                    System.out.print("S");
                else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex())
                    System.out.print("E");
                else
                System.out.print(mazeMatrix[i][j]);
            System.out.println();
        }
    }
    public boolean isValidLocation(int wantedRow , int wantedColumn)
    {
        if (wantedRow<0 || wantedRow>=mazeMatrix.length || wantedColumn<0 || wantedColumn>=mazeMatrix[0].length) return false;
        return true;
    }

}
