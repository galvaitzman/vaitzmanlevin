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
        if(s == null || s.getStartState() == null || s.getGoalState() == null)
            return null;

        boolean solutionFound = false;
        AState start = s.getStartState();
        openList.add(start);
        openListMap.put(start.getState(),start);
        AState aState = null;
        while (!solutionFound && !openList.isEmpty())
        {
            aState= popOpenList();
            openListMap.remove(aState.getState());
            if (aState.getState().equals("GOAL"))
                solutionFound = true;
            else{
                ArrayList <AState> allSuccessors = s.getAllPossibleStates(aState);
                while (!allSuccessors.isEmpty()){
                    AState as = allSuccessors.remove(0);
                    if (!closeListMap.containsKey(as.getState()) && !openListMap.containsKey(as.getState()) ){
                        openList.add(as);
                        openListMap.put(as.getState(),as);
                    }
                }
            }
            closeListMap.put(aState.getState(),aState);
        }

        if(!solutionFound)
            return null;

        return generateSolution(aState, s);
    }

    @Override
    public AState popOpenList()
    {
        visitedNodes += 1;
        return openList.poll();
    }
}
