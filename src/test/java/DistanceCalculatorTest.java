import org.junit.Test;
import org.junit.Ignore;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DistanceCalculatorTest {
    @Test
    public void testCalculatorHasGraph() {
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();

        DistanceCalculator calc = new DistanceCalculator(testGraph);

        assertEquals(testGraph, calc.graph);
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
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();

        DistanceCalculator calc = new DistanceCalculator(testGraph);

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
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();

        DistanceCalculator calc = new DistanceCalculator(testGraph);

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
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();

        DistanceCalculator calc = new DistanceCalculator(testGraph);

        assertTrue(calc.routeExists(Arrays.asList('A', 'B')));
        assertTrue(calc.routeExists(Arrays.asList('A', 'B', 'C')));
        assertTrue(calc.routeExists(Arrays.asList('A', 'B', 'C', 'D')));

        assertFalse(calc.routeExists(Arrays.asList('A', 'C')));
        assertFalse(calc.routeExists(Arrays.asList('A', 'B', 'D')));
        assertFalse(calc.routeExists(Arrays.asList('A', 'B', 'C', 'A')));
    }

    @Test
    public void testItCanDetectCircularRoutes() {
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();
        DistanceCalculator calc = new DistanceCalculator(testGraph);

        assertTrue(calc.routeExists(Arrays.asList('D', 'E', 'B', 'C', 'D')));
        assertTrue(calc.routeExists(Arrays.asList('C', 'D', 'C')));

        assertFalse(calc.routeExists(Arrays.asList('C', 'C')));
        assertFalse(calc.routeExists(Arrays.asList('D', 'D')));
    }

    @Test
    public void testItCanDetectNoRoute() {
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();
        DistanceCalculator calc = new DistanceCalculator(testGraph);

        List<Character> validRoute = Arrays.asList('A', 'B', 'C', 'E');
        List<Character> invalidRoute = Arrays.asList('A', 'B', 'C', 'A');

        assertEquals("11", calc.calculateDistance(validRoute));
        assertEquals("NO SUCH ROUTE", calc.calculateDistance(invalidRoute));
    }

    private Map<Character, HashMap<Character, Integer>> createTestGraph() {
        Map<Character, HashMap<Character, Integer>> graph
            = new HashMap<Character, HashMap<Character, Integer>>() {
                {
                    put('A', new HashMap<Character, Integer>() {
                        {
                            put('B', 5);
                            put('D', 5);
                            put('E', 7);
                        }
                    });
                    put('B', new HashMap<Character, Integer>() {
                        {
                            put('C', 4);
                        }
                    });
                    put('C', new HashMap<Character, Integer>() {
                        {
                            put('D', 8);
                            put('E', 2);
                        }
                    });
                    put('D', new HashMap<Character, Integer>() {
                        {
                            put('C', 8);
                            put('E', 6);
                        }
                    });
                    put('E', new HashMap<Character, Integer>() {
                        {
                            put('B', 3);
                        }
                    });
                }
            };
     return graph;
    }
}
