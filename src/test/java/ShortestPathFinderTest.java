import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ShortestPathFinderTest {
    private Map<Character, HashMap<Character, Integer>> exampleGraph;
    private ShortestPathFinder finder;

    @Before
    public void createGraph() {
        exampleGraph = ExampleGraph.createGraph();
        finder = new ShortestPathFinder(exampleGraph);
    }

    @Test
    public void testPathFinderCanGetAdjacentNodes() {
        assertEquals(Arrays.asList('B', 'D', 'E'), finder.getAdjacentNodes('A'));
        assertEquals(Arrays.asList('C'), finder.getAdjacentNodes('B'));
        assertEquals(Arrays.asList('C', 'E'), finder.getAdjacentNodes('D'));
    }

    @Test
    public void testGeneratingCosts() {
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
        assertEquals((Character)'B', finder.getLowestCostNode('A', 'B', null, null));
    }

    @Test
    public void testItCanFindTheShortestPath() {
        assertEquals((Integer)7, finder.findShortestPathDistance('A', 'E'));
        assertEquals((Integer)9, finder.findShortestPathDistance('A', 'C'));
        assertEquals((Integer)9, finder.findShortestPathDistance('B', 'B'));
        assertEquals((Integer)8, finder.findShortestPathDistance('C', 'D'));
        assertEquals((Integer)9, finder.findShortestPathDistance('C', 'C'));
    }

}

