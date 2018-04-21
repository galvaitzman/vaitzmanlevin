package algorithms.search;

public abstract class AState {
////// goooooonnniiiiii
    // gal
    //// checkkkkkk
    private String state;
    private double cost;
    private AState cameFrom;

    public AState(String state,AState cameFrom,double cost)
    {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }
    public String getState()
    {
        return state;
    }
    public double getCost()
    {
        return cost;
    }
    public AState getCameFrom()
    {
        return cameFrom;
    }
    public void setState(String newState)
    {
        this.state = newState;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
