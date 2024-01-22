package plan;

import map.Graph;
import map.MapNode;

/**
 * A class defining heuristic for A* search
 */
public class AStarHeuristic extends Heuristic {
    /**
     * Initializer
     *
     * @param graph a graph object
     */
    public AStarHeuristic(Graph graph) {
        super(graph);
    }

    /**
     * Returns the estimated cost from the current node to the goal node (heuristic function)
     *
     * @param node     the current node
     * @param goalNode the goal node
     * @return the estimated cost
     */
    public double getHeuristics(MapNode node, MapNode goalNode) {
        // TODO
        double Earth_Radius = 6372.8; // in kilometers
        double degDifLongitude = 0.0D;
        double degDifLatitude = 0.0D;
        double radDifLongitude = 0.0D;
        double radDifLatitude = 0.0D;
        double radToLatitude = 0.0D;
        double radFromLatitude = 0.0D;

        // Longitude Difference
        degDifLongitude = (goalNode.longitude - node.longitude);

        // Latitude Difference
        degDifLatitude = (goalNode.latitude - node.latitude);

        // Cost Longitude (convert from degrees to radians)
        // radDifLongitude = (degDifLongitude) * (Math.PI/180);
        radDifLongitude = Math.toRadians(degDifLongitude);

        // Cost Latitude (convert from degrees to radians)
        radDifLatitude = Math.toRadians(degDifLatitude);

        // To Latitude
        radToLatitude = Math.toRadians(goalNode.longitude);

        // From Latitude
        radFromLatitude = Math.toRadians(node.latitude);

        // Harvesine Formula
        double a = Math.pow(Math.sin(radDifLatitude/2), 2) + Math.cos(radToLatitude) * Math.cos(radFromLatitude) * Math.pow(Math.sin(radDifLongitude/2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return Earth_Radius * c;
    }
}

