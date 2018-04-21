package algorithms.search;
import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm
{
    private Stack<AState> openList;

    public DepthFirstSearch() {
        super();
        openList = new Stack<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        boolean solutionFound = false;
        openList.add(s.getStartState());
        AState aState = null;
        while (!solutionFound && !openList.isEmpty())
        {
            aState= popOpenList();
            if (aState.getState().equals("GOAL"))
                solutionFound = true;
            else {
                ArrayList<AState> as = s.getAllSuccessors(aState);
                while (!as.isEmpty())
                    openList.add(as.remove(as.size() - 1));
            }
        }
        if(!solutionFound)
            throw new RuntimeException("the solver didn't found the solution");

        return generateSolution(aState,s);
    }

    @Override
    public AState popOpenList()
    {
        visitedNodes += 1;
        return openList.pop();
    }
}
