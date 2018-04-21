package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> openList;

    public BreadthFirstSearch()
    {
        super();
        openList = new LinkedList<AState>();
    }

    @Override
    public Solution solve(ISearchable s)
    {
        boolean solutionFound=false;
        openList.add(s.getStartState());
        AState aState = null;
        while (!solutionFound && !openList.isEmpty())
        {
            aState= popOpenList();
            if (aState.getState().equals("GOAL"))
                solutionFound = true;
            else
                openList.addAll(s.getAllSuccessors(aState));
        }

        if(!solutionFound)
            throw new RuntimeException("the solve didn't found the solution ");

        return generateSolution(aState, s);
    }

    @Override
    public AState popOpenList()
    {
        visitedNodes += 1;
        return openList.poll();
    }


}
