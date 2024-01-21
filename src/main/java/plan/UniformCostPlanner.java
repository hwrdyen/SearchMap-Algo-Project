package plan;

import map.MapEdge;
import map.MapNode;

/**
 * A class defining planning using Uniform Cost Search
 */
public class UniformCostPlanner extends Planner {
    /**
     * heuristics used for Uniform Cost Search
     */
    CostFunction costFunction;

    /**
     * Initializer
     *
     * @param costFunction a costFunction object
     */
    public UniformCostPlanner(CostFunction costFunction) {
        super();
        //TODO
    }

    /**
     * Runs Uniform Cost Search
     *
     * @param startNode the start node
     * @param goalNode  the goal node
     * @return a list of MapNode objects
     */
    @Override
    public PlanResult plan(MapNode startNode, MapNode goalNode) {
        //TODO
        return null;
    }

    /**
     * Gets the name of the planner
     *
     * @return planner name
     */
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
