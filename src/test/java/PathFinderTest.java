import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PathFinderTest {
    private Map<Character, HashMap<Character, Integer>> exampleGraph;
    private PathFinder finder;

    @Before
    public void createGraph() {
        exampleGraph = ExampleGraph.createGraph();
        finder = new PathFinder(exampleGraph);
    }

    @Test
    public void testCanFindAdjacentNodes() {
        List<Character> expected = Arrays.asList('B', 'D', 'E');

        assertEquals(expected, finder.findAdjacentNodes('A'));
    }

    @Test
    public void testFindingPathsFromCtoCWithMaxStops() {
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

    @Test
    public void testFindingPathsWithinACertainDistance() {
        ArrayList<List<Character>> expectedPaths
            = new ArrayList<List<Character>>() {
                {
                    add(Arrays.asList('C', 'D', 'C'));
                    add(Arrays.asList('C', 'E', 'B', 'C'));
                    add(Arrays.asList('C', 'E', 'B', 'C', 'D', 'C'));
                    add(Arrays.asList('C', 'D', 'C', 'E', 'B', 'C'));
                    add(Arrays.asList('C', 'D', 'E', 'B', 'C'));
                    add(Arrays.asList('C', 'E', 'B', 'C', 'E', 'B', 'C'));
                    add(Arrays.asList('C', 'E', 'B', 'C', 'E', 'B', 'C', 'E', 'B', 'C'));
                }
            };

        ArrayList<List<Character>> result
            = finder.findPathsUpToMaxDistance('C', 'C', 30);

        for (List<Character> path : expectedPaths) {
            assertTrue(result.contains(path));
        }
        assertEquals((int)7, result.size());
    }
}
