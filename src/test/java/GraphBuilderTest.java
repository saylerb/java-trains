import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GraphBuilderTest {
    @Test
    public void getGraphInput() {
        GraphBuilder builder = new GraphBuilder("AB");

        assertEquals("AB", builder.getInput());
    }

    @Test
    public void buildWeightedGraphWithSingleNode() {
        Map<Character, HashMap<Character, Integer>> expected
            = new HashMap<Character, HashMap<Character, Integer>>() {
                {
                    put('A', new HashMap<Character, Integer>() {
                        {
                            put('B', 5);
                        }
                    });
                }
            };
        Graph expectedGraph = new Graph(expected);
        GraphBuilder builder = new GraphBuilder("AB5");

        Graph actualGraph = builder.buildWeighted();

        assertTrue(expectedGraph.equals(actualGraph));
    }

    @Test
    public void buildWeightedGraphWithMultipleNodes() {
        Graph expectedGraph;
        expectedGraph = ExampleGraph.createGraph();

        GraphBuilder builder
            = new GraphBuilder("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

        Graph actualGraph = builder.buildWeighted();

        assertTrue(expectedGraph.equals(actualGraph));
    }
}
