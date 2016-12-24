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

    @Test
    public void testGeneratingInitialCosts() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        ShortestPathFinder finder = new ShortestPathFinder(exampleGraph);

        Integer infinity = Integer.MAX_VALUE;

        HashMap<Character, Integer> expectedOne
            = new HashMap<Character, Integer>() {
            {
                put('A', infinity);
                put('B', 5);
                put('C', infinity);
                put('D', 5);
                put('E', 7);
            }
            };

        HashMap<Character, Integer> expectedTwo
            = new HashMap<Character, Integer>() {
            {
                put('A', infinity);
                put('B', infinity);
                put('C', infinity);
                put('D', 8);
                put('E', 2);
            }
            };

        assertEquals(expectedOne, finder.generateInitialCosts('A', 'C'));
        assertEquals(expectedTwo, finder.generateInitialCosts('C', 'B'));
    }
}
