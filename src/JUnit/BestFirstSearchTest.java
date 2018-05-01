import algorithms.*;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    public void solve(){
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(5,1);
        int row = maze.getGoalPosition().getRowIndex();
        int column = maze.getGoalPosition().getColumnIndex();
        maze.setValueAt(row,column,1);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }

}