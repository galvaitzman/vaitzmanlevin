package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm
{
    protected int visitedNodes;
    protected Map <String,AState> openListMap;
    protected Map <String,AState> closeListMap;

    public ASearchingAlgorithm()
    {
        visitedNodes=0;
        openListMap = new HashMap<String, AState>();
        closeListMap = new HashMap<String, AState>();
    }

    @Override
    public abstract AState popOpenList();

    @Override
    public abstract Solution solve(ISearchable s);

    @Override
    public int getNumberOfNodesEvaluated()
    {
        return visitedNodes;
    }

    protected Solution generateSolution(AState aState, ISearchable s)
    {
        ArrayList<AState> solutionPath = new ArrayList<>();
        while ((aState.getCameFrom() != null) && !aState.getState().equals("START"))
        {
            solutionPath.add(aState);
            aState=aState.getCameFrom();
        }

        solutionPath.add(s.getStartState());
        Collections.reverse(solutionPath);
        return (new Solution(solutionPath));
    }

    public String getName()
    {
        return this.getClass().getSimpleName();
    }
}
