import org.junit.Test;
import org.junit.Ignore;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {
    @Test
    public void testCalculatorHasGraph() {
        Map<Character, HashMap<Character, Integer>> testGraph
            = createTestGraph();

        DistanceCalculator calc = new DistanceCalculator(testGraph);

        assertEquals(testGraph, calc.graph);
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
