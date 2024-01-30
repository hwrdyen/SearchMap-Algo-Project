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
//            for (Map.Entry<MapNode, Double> entry: edgeCosts_hashmap.entrySet()) {
//                if(entry.getValue().equals(costEffect_edge)) {
//                    costEffect_selectednode = entry.getKey();
//                    break;
//                }
//            }

            // =========== testing UCS =========== //
            ArrayList<MapNode> selectednodeList = new ArrayList<>();
            for (Map.Entry<MapNode, Double> entry: edgeCosts_hashmap.entrySet()) {
                if(entry.getValue().equals(costEffect_edge)) {
                    selectednodeList.add(entry.getKey());
                }
            }

            if (selectednodeList.size() == 1) {
                costEffect_selectednode = selectednodeList.get(0);
            } else if (selectednodeList.size() > 1) {
                System.out.println("hi");
                Long smallest_id = Long.MAX_VALUE;
                MapNode smallest_node = null;
                for (int i = 0; i < selectednodeList.size(); i++) {
                    if (selectednodeList.get(i).id < smallest_id) {
                        smallest_id = selectednodeList.get(i).id;
                        smallest_node = selectednodeList.get(i);
                    }
                }
                costEffect_selectednode = smallest_node;
            }
            // =========== testing UCS =========== //

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
                // =========== testing UCS =========== //
                else {
                    // current edge's cost
                    Double currentedge_cost = costFunction.getCost(edge);
                    // previous edge's sum cost
                    Double previousedge_cost = edgeCosts_hashmap.get(sourceNode);

                    // sum of cost from root to this current node
                    Double totaledge_cost = previousedge_cost + currentedge_cost;

                    if (totaledge_cost < edgeCosts_hashmap.get(nextNode)) {
                        edge_priorityqueue.remove(edgeCosts_hashmap.get(nextNode));
                        edgeCosts_hashmap.remove(nextNode);
                        edgeCosts_hashmap.put(nextNode, totaledge_cost);
                        edge_priorityqueue.add(totaledge_cost);
                    }
                }
                // =========== testing UCS =========== //
            }
            // =========== testing UCS =========== //
//            edgeCosts_hashmap.remove(costEffect_selectednode);
            // =========== testing UCS =========== //
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
