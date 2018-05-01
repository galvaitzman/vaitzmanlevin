package algorithms.search;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch()
    {
        openList = new PriorityQueue<>(Comparator.comparing(AState::getCost));
        openListMap = new HashMap<String, AState>();
        closeListMap = new HashMap<String, AState>();
    }
}

