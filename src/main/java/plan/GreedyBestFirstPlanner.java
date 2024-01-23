package plan;

import map.MapEdge;
import map.MapNode;

import java.util.*;

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
        this.heuristic = heuristic;
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
        HashMap<MapNode, MapNode> parents = new HashMap<>();
        Set<MapNode> expandedNodes = new HashSet<>();

        // work as a library when shortest heuristic is selected and expanded -->
        // find the corresponding node in this HashMap -->
        // remove the corresponding node
        HashMap<MapNode, Double> edgeHeuristic_hashmap =  new HashMap<>();

        // create a priority queue to store heuristic
        PriorityQueue<Double> edge_priorityqueue = new PriorityQueue<>();

        parents.put(startNode, null);
        // edgeHeuristic stores node and its corresponding heuristic value
        edgeHeuristic_hashmap.put(startNode, heuristic.getHeuristics(startNode, goalNode));
        edge_priorityqueue.add(heuristic.getHeuristics(startNode, goalNode));

        while(!edge_priorityqueue.isEmpty()) {
            Double shortestHeuristic_edge = edge_priorityqueue.poll();
            MapNode shortestHeuristic_selectednode = null;
            for (Map.Entry<MapNode, Double> entry: edgeHeuristic_hashmap.entrySet()) {
                if(entry.getValue().equals(shortestHeuristic_edge)) {
                    shortestHeuristic_selectednode = entry.getKey();
                    break;
                }
            }
            expandedNodes.add(shortestHeuristic_selectednode);
            if(shortestHeuristic_selectednode.id == goalNode.id) {
                return new PlanResult(expandedNodes.size(), getNodeList(parents, goalNode));
            }

            for (MapEdge edge : shortestHeuristic_selectednode.edges) {
                MapNode nextNode = edge.destinationNode;
                if (!parents.containsKey(nextNode)) {
                    parents.put(nextNode, shortestHeuristic_selectednode);

                    // HashMap should store the node and its corresponding heuristic
                    edgeHeuristic_hashmap.put(nextNode, heuristic.getHeuristics(nextNode, goalNode));

                    // the lowest heuristic will be selected first
                    edge_priorityqueue.add(heuristic.getHeuristics(nextNode, goalNode));
                }
            }
            edgeHeuristic_hashmap.remove(shortestHeuristic_selectednode);
        }

        return new PlanResult(expandedNodes.size(), null);
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
