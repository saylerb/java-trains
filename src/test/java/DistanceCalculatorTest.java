import org.junit.Test;
import org.junit.Ignore;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
        Integer expected = 5;

        assertEquals(expected, calc.calculateDistance(route));
    }

    @Test
    public void testGetNodePairs() {
        ArrayList<List<Character>> expectedPairs = new ArrayList<List<Character>>() {
            {
                add(Arrays.asList('A', 'B'));
                add(Arrays.asList('B', 'C'));
            }
        };

        DistanceCalculator calc = new DistanceCalculator(null);
        List<Character> route = Arrays.asList('A', 'B', 'C');

        assertEquals(expectedPairs, calc.getNodePairs(route));
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
