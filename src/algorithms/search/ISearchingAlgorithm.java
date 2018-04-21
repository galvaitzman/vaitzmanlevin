package algorithms.search;

public interface ISearchingAlgorithm {

    AState popOpenList();
    Solution solve(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();
}
