package plan;

import map.MapEdge;
import map.MapNode;

import java.util.*;

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
        this.costFunction = costFunction;
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
        HashMap<MapNode, MapNode> parents = new HashMap<>();
        Set<MapNode> expandedNodes = new HashSet<>();

        // work as a library when shortes edge is selected and expanded -->
        // find the corresponding node in this HashMap -->
        // remove the corresponding node
        HashMap<MapNode, Double> edgeCosts_hashmap =  new HashMap<>();

        // create a priority queue to store edge length
        PriorityQueue<Double> edge_priorityqueue = new PriorityQueue<>();

        parents.put(startNode, null);
        edgeCosts_hashmap.put(startNode, 0.0D);
        edge_priorityqueue.add(0.0D);

        while(!edge_priorityqueue.isEmpty()) {
            Double costEffect_edge = edge_priorityqueue.poll();
            MapNode costEffect_selectednode = null;
            for (Map.Entry<MapNode, Double> entry: edgeCosts_hashmap.entrySet()) {
                if(entry.getValue().equals(costEffect_edge)) {
                    costEffect_selectednode = entry.getKey();
                    break;
                }
            }
            expandedNodes.add(costEffect_selectednode);
            if(costEffect_selectednode.id == goalNode.id) {
                return new PlanResult(expandedNodes.size(), getNodeList(parents, goalNode));
            }

            for (MapEdge edge : costEffect_selectednode.edges) {
                MapNode nextNode = edge.destinationNode;
                MapNode sourceNode = edge.sourceNode;
                if (!parents.containsKey(nextNode)) {
                    parents.put(nextNode, costEffect_selectednode);

                    // current edge's cost
                    Double currentedge_cost = costFunction.getCost(edge);
                    // previous edge's sum cost
                    Double previousedge_cost = edgeCosts_hashmap.get(sourceNode);

                    // sum of cost from root to this current node
                    Double totaledge_cost = previousedge_cost + currentedge_cost;

                    // HashMap should store the node and its corresponding sum cost
                    edgeCosts_hashmap.put(nextNode, totaledge_cost);
                    // should compare the sum of cost and not only one edge cost
                    // the lowest edge_cost will be selected first
                    edge_priorityqueue.add(totaledge_cost);
                }
            }
            edgeCosts_hashmap.remove(costEffect_selectednode);
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
