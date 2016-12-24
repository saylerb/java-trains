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
    public void testFindingPathFromCtoC() {
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

        assertEquals(expected, finder.findPathWithMax('C', 'C', 3));
    }
}
