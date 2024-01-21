import map.Graph;
import map.MapNode;
import org.junit.Test;
import plan.BFSPlanner;
import plan.Planner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void sampleTest() {
        String osmFile = "./data/toronto.osm";
        String cyclistsAccidentFile = "./data/Cyclists.csv";
        Graph torontoGraph = new Graph(osmFile, cyclistsAccidentFile);

        Planner bfsPlanner = new BFSPlanner();

        //Manually specify sourceNode and endNode
        long sourceNodeId = 6374148719L;
        long endNodeId = 6374051128L;
        MapNode sourceNode = torontoGraph.nodes.get(sourceNodeId);
        MapNode endNode = torontoGraph.nodes.get(endNodeId);
        List<MapNode> nodeList = bfsPlanner.plan(sourceNode, endNode).path;
        List<Long> actual = new ArrayList<>();
        for (MapNode node : nodeList) {
            actual.add(node.id);
        }

        List<Long> expected = Arrays.asList(6374148719L, 6662926503L, 389678174L, 389678175L, 1480794735L, 389678176L,
                3983181527L, 3983181528L, 389678212L, 389678213L, 389678214L, 389678215L, 389678216L, 7311057931L,
                389678220L, 389678221L, 389678222L, 389677908L, 749952029L, 389677909L, 389677910L,
                389677911L, 389677912L, 391186184L, 389677913L, 389677914L, 6374051128L);

        assertEquals(expected, actual);
    }
}
