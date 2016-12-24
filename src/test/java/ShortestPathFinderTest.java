import org.junit.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShortestPathFinderTest {
    @Test
    public void testPathFinderHasGraph() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        ShortestPathFinder finder = new ShortestPathFinder(exampleGraph);

        assertEquals(exampleGraph, finder.getGraph());
    }

    @Test
    public void testPathFinderCanGetAdjacentNodes() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        ShortestPathFinder finder = new ShortestPathFinder(exampleGraph);

        assertEquals(Arrays.asList('B', 'D', 'E'), finder.getAdjacentNodes('A'));
        assertEquals(Arrays.asList('C'), finder.getAdjacentNodes('B'));
        assertEquals(Arrays.asList('C', 'E'), finder.getAdjacentNodes('D'));
    }
}
