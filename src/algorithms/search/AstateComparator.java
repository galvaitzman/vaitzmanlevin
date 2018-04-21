package algorithms.search;
import java.util.Comparator;



public class AstateComparator implements Comparator<AState>
{
    @Override
    public int compare(AState x, AState y) {
        if (x.getCost() < y.getCost()) {
            return -1;
        }
        return 1;
    }
}
