package com.company;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {


        //testMazeGenerator(new SimpleMazeGenerator());
        long startTime = System.currentTimeMillis();
        testMazeGenerator(new MyMazeGenerator());
        long endtTime = System.currentTimeMillis();
        System.out.println(String.format("total time: %s",endtTime-startTime));
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        maze.print();
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





