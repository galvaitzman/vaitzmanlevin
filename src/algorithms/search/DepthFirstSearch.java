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
                ArrayList <AState> allSuccessors = s.getAllSuccessors(aState);
                while (!allSuccessors.isEmpty()){
                    AState as = allSuccessors.remove(allSuccessors.size()-1);
                    if (!closeListMap.containsKey(as.getState()) && !openListMap.containsKey(as.getState()) ){
                        openList.add(as);
                        openListMap.put(as.getState(),as);
                    }
                }
            }
            closeListMap.put(aState.getState(),aState);
        }
        if(!solutionFound) throw new RuntimeException("the solver didn't found the solution");
        return generateSolution(aState,s);
    }

    @Override
    public AState popOpenList()
    {
        visitedNodes += 1;
        return openList.pop();
    }
}
