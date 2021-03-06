package algorithms.search;


import java.util.Comparator;

public abstract class AState{

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

    @Override
    public boolean equals(Object other)
    {
        AState a = (AState)other;
        return (a.getState().equals(this.getState()));
    }
}
