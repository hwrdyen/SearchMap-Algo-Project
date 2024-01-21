package plan;

import map.MapEdge;
import map.MapNode;

/**
 * A class defining planning using Best First Search
 */
public class GreedyBestFirstPlanner extends Planner {
    /**
     * heuristics used for Best First Search
     */
    Heuristic heuristic;

    /**
     * Initializer
     *
     * @param heuristic a heuristic object
     */
    public GreedyBestFirstPlanner(Heuristic heuristic) {
        super();
        //TODO
    }

    /**
     * Runs Best First Search
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
