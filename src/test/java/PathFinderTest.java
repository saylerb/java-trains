import org.junit.Test;
import org.junit.Ignore;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PathFinderTest {
    @Test
    public void testPathFinderHasGraph() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        PathFinder finder = new PathFinder(exampleGraph);

        assertEquals(exampleGraph, finder.getGraph());
    }

    @Test
    public void testCanFindAdjacentNodes() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        PathFinder finder = new PathFinder(exampleGraph);

        List<Character> expected = Arrays.asList('B', 'D', 'E');

        assertEquals(expected, finder.findAdjacentNodes('A'));
    }

    @Test
    public void testFindingPathsFromCtoCWithMaxStops() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        PathFinder finder = new PathFinder(exampleGraph);

        ArrayList<List<Character>> expected
            = new ArrayList<List<Character>>() {
            {
                add(Arrays.asList('C', 'D', 'C'));
                add(Arrays.asList('C', 'E', 'B', 'C'));
            }
        };

        ArrayList<List<Character>> result
            = finder.findPathsWithMaxStops('C', 'C', 3);

        assertEquals(expected, result);
        assertEquals((int)2, result.size());
    }

    @Test
    public void testFindingPathsFromAtoCWithExactStops() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        PathFinder finder = new PathFinder(exampleGraph);

        ArrayList<List<Character>> expected
            = new ArrayList<List<Character>>() {
            {
                add(Arrays.asList('A', 'B', 'C', 'D', 'C'));
                add(Arrays.asList('A', 'D', 'C', 'D', 'C'));
                add(Arrays.asList('A', 'D', 'E', 'B', 'C'));
            }
        };

        ArrayList<List<Character>> result
            = finder.findPathsWithExactStops('A', 'C', 4);

        assertEquals(expected, result);
        assertEquals((int)3, result.size());
    }
}
