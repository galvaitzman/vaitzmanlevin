package algorithms.search;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch(){
        openList = new PriorityQueue<>(new AstateComparator());
    }
}

