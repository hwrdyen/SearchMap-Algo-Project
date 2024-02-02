package plan;

import map.MapEdge;
import map.MapNode;

import java.util.*;

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
        this.heuristic = heuristic;
        this.costFunction = costFunction;
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
        HashMap<MapNode, MapNode> parents = new HashMap<>();
        Set<MapNode> expandedNodes = new HashSet<>();

        // work as a library when shortest Astar = (heuristic + cost) is selected and expanded -->
        // find the corresponding node in this HashMap -->
        // remove the corresponding node
        HashMap<MapNode, Double> edgeAStar_hashmap =  new HashMap<>();

        // create a priority queue to store Astar
        PriorityQueue<Double> edge_priorityqueue = new PriorityQueue<>();

        parents.put(startNode, null);
        // edgeAStar stores node and its corresponding AStar = (heuristic + cost) value
        edgeAStar_hashmap.put(startNode, heuristic.getHeuristics(startNode, goalNode) + 0.0D);
        edge_priorityqueue.add(heuristic.getHeuristics(startNode, goalNode) + 0.0D);

        while(!edge_priorityqueue.isEmpty()) {
            Double smallestAStar_edge = edge_priorityqueue.poll();
            MapNode smallestAStar_selectednode = null;
            for (Map.Entry<MapNode, Double> entry: edgeAStar_hashmap.entrySet()) {
                if(entry.getValue().equals(smallestAStar_edge)) {
                    smallestAStar_selectednode = entry.getKey();
                    break;
                }
            }
            expandedNodes.add(smallestAStar_selectednode);
            if(smallestAStar_selectednode.id == goalNode.id) {
                return new PlanResult(expandedNodes.size(), getNodeList(parents, goalNode));
            }

            for (MapEdge edge : smallestAStar_selectednode.edges) {
                MapNode nextNode = edge.destinationNode;
                MapNode sourceNode = edge.sourceNode;
                if (!parents.containsKey(nextNode)) {
                    parents.put(nextNode, smallestAStar_selectednode);

                    // current edge's cost
                    Double currentedge_cost = costFunction.getCost(edge);
                    // previous edge's sum cost
                    Double previousedge_cost = edgeAStar_hashmap.get(sourceNode);

                    // sum of cost from root to this current node
                    Double totaledge_cost = previousedge_cost + currentedge_cost;

                    // AStar = heuristic + sum_of_cost
                    Double AStar_value =  (0.01 * heuristic.getHeuristics(nextNode, goalNode)) + (0.99 * totaledge_cost);

                    // HashMap should store the node and its corresponding AStar value
                    edgeAStar_hashmap.put(nextNode, AStar_value);

                    // the lowest AStar will be selected first
                    edge_priorityqueue.add(AStar_value);
                }
                else {
                    // current edge's cost
                    Double currentedge_cost = costFunction.getCost(edge);
                    // previous edge's sum cost
                    Double previousedge_cost = edgeAStar_hashmap.get(sourceNode);

                    // sum of cost from root to this current node
                    Double totaledge_cost = previousedge_cost + currentedge_cost;

                    // AStar = heuristic + sum_of_cost
                    Double AStar_value = (0.01 * heuristic.getHeuristics(nextNode, goalNode)) + (0.99 * totaledge_cost);

                    if (AStar_value < edgeAStar_hashmap.get(nextNode)) {
                        parents.remove(nextNode);
                        edge_priorityqueue.remove(edgeAStar_hashmap.get(nextNode));
                        edgeAStar_hashmap.remove(nextNode);
                        edgeAStar_hashmap.put(nextNode, AStar_value);
                        edge_priorityqueue.add(AStar_value);
                        parents.put(nextNode, smallestAStar_selectednode);
                    }
                }
            }
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
