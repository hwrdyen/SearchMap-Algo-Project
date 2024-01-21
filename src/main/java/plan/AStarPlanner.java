package plan;

import map.MapEdge;
import map.MapNode;

/**
 * A class defining planning using A* search
 */
public class AStarPlanner extends Planner {
    /**
     * heuristics used for A*
     */
    Heuristic heuristic;
    /**
     * cost function used for A*
     */
    CostFunction costFunction;

    /**
     * Initializer
     *
     * @param heuristic a heuristic object
     * @param costFunction    cost function option
     */
    public AStarPlanner(Heuristic heuristic, CostFunction costFunction) {
        super();
        //TODO
    }

    /**
     * Runs A* search
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
