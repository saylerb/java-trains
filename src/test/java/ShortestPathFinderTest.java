import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
    public void testGeneratingCosts() {
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

        assertEquals(expectedOne, finder.generateCosts('A', 'C'));
        assertEquals(expectedTwo, finder.generateCosts('C', 'B'));
    }

    @Test
    public void testGeneratingPredecessors() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        ShortestPathFinder finder = new ShortestPathFinder(exampleGraph);

        HashMap<Character, Character> expectedOne
            = new HashMap<Character, Character>() {
                {
                    put('B', 'A');
                    put('C', null);
                    put('D', 'A');
                    put('E', 'A');
                }
            };

        HashMap<Character, Character> expectedTwo
            = new HashMap<Character, Character>() {
                {
                    put('A', null);
                    put('B', null);
                    put('D', 'C');
                    put('E', 'C');
                }
            };

        assertEquals(expectedOne, finder.generatePredecessors('A', 'B'));
        assertEquals(expectedTwo, finder.generatePredecessors('C', 'B'));
    }

    @Test
    public void testItCanFindTheLowestCostNode() {

        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        ShortestPathFinder finder = new ShortestPathFinder(exampleGraph);

        assertEquals((Character)'B', finder.getLowestCostNode('A', 'B'));
    }
}

