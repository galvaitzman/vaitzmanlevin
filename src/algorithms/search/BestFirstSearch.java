package algorithms.search;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch()
    {
        openList = new PriorityQueue<>(new AstateComparator());
        openListMap = new HashMap<String, AState>();
        closeListMap = new HashMap<String, AState>();
    }
}

