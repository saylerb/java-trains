import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DistanceCalculatorTest {
    @Test
    public void testCalculatorHasGraph() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        assertNotNull(exampleGraph);
        assertEquals(exampleGraph, calc.getGraph());
    }

    @Test
    public void testCalculatesDistanceBetweenNodes() {
        Map<Character, HashMap<Character, Integer>> testGraph
            = new HashMap<Character, HashMap<Character, Integer>>() {
                {
                    put('A', new HashMap<Character, Integer>() {
                        {
                            put('B', 5);
                        }
                    });
                }
            };

        DistanceCalculator calc = new DistanceCalculator(testGraph);
        List<Character> route = Arrays.asList('A', 'B');

        assertEquals("5", calc.calculateDistance(route));
    }

    @Test
    public void testGetNodePairs() {
        ArrayList<List<Character>> expectedPairs
            = new ArrayList<List<Character>>() {
                {
                    add(Arrays.asList('A', 'B'));
                    add(Arrays.asList('B', 'C'));
                    add(Arrays.asList('C', 'A'));
                }
            };

        DistanceCalculator calc = new DistanceCalculator(null);
        List<Character> route = Arrays.asList('A', 'B', 'C', 'A');

        assertEquals(expectedPairs, calc.getNodePairs(route));
    }

    @Test
    public void calculateTotalRouteDistance() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();


        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        List<Character> routeOne = Arrays.asList('A', 'D');
        List<Character> routeTwo = Arrays.asList('A', 'B', 'C');
        List<Character> routeThree = Arrays.asList('A', 'D', 'C');
        List<Character> routeFour = Arrays.asList('A', 'B', 'C', 'D');
        List<Character> routeFive = Arrays.asList('A', 'E', 'B', 'C', 'D');

        assertEquals("5", calc.calculateDistance(routeOne));
        assertEquals("9", calc.calculateDistance(routeTwo));
        assertEquals("13", calc.calculateDistance(routeThree));
        assertEquals("17", calc.calculateDistance(routeFour));
        assertEquals("22", calc.calculateDistance(routeFive));
    }

    @Test
    public void testIfEdgeExists() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        assertTrue(calc.edgeExists(Arrays.asList('A', 'B')));
        assertTrue(calc.edgeExists(Arrays.asList('B', 'C')));
        assertTrue(calc.edgeExists(Arrays.asList('C', 'D')));
        assertTrue(calc.edgeExists(Arrays.asList('D', 'E')));

        assertFalse(calc.edgeExists(Arrays.asList('A', 'A')));
        assertFalse(calc.edgeExists(Arrays.asList('C', 'B')));
        assertFalse(calc.edgeExists(Arrays.asList('D', 'B')));
        assertFalse(calc.edgeExists(Arrays.asList('Z', 'X')));
    }

    @Test
    public void testIfRouteExists() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        assertTrue(calc.routeExists(Arrays.asList('A', 'B')));
        assertTrue(calc.routeExists(Arrays.asList('A', 'B', 'C')));
        assertTrue(calc.routeExists(Arrays.asList('A', 'B', 'C', 'D')));

        assertFalse(calc.routeExists(Arrays.asList('A', 'C')));
        assertFalse(calc.routeExists(Arrays.asList('A', 'B', 'D')));
        assertFalse(calc.routeExists(Arrays.asList('A', 'B', 'C', 'A')));
    }

    @Test
    public void testItCanDetectCircularRoutes() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        assertTrue(calc.routeExists(Arrays.asList('D', 'E', 'B', 'C', 'D')));
        assertTrue(calc.routeExists(Arrays.asList('C', 'D', 'C')));

        assertFalse(calc.routeExists(Arrays.asList('C', 'C')));
        assertFalse(calc.routeExists(Arrays.asList('D', 'D')));
    }

    @Test
    public void testItCanCalculateDistanceOfCircularRoute() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        List<Character> routeOne = Arrays.asList('C', 'D', 'C');
        List<Character> routeTwo = Arrays.asList('C', 'D', 'C', 'D');
        List<Character> routeThree = Arrays.asList('B', 'C', 'E', 'B');
        List<Character> routeFour = Arrays.asList('C', 'D', 'E', 'B', 'C');

        assertEquals("16", calc.calculateDistance(routeOne));
        assertEquals("24", calc.calculateDistance(routeTwo));
        assertEquals("9", calc.calculateDistance(routeThree));
        assertEquals("21", calc.calculateDistance(routeFour));
    }

    @Test
    public void testItCanDetectNoRoute() {
        Map<Character, HashMap<Character, Integer>> exampleGraph
            = ExampleGraph.createGraph();

        DistanceCalculator calc = new DistanceCalculator(exampleGraph);

        List<Character> validRoute = Arrays.asList('A', 'B', 'C', 'E');
        List<Character> invalidRoute = Arrays.asList('A', 'B', 'C', 'A');

        assertEquals("11", calc.calculateDistance(validRoute));
        assertEquals("NO SUCH ROUTE", calc.calculateDistance(invalidRoute));
    }
}
