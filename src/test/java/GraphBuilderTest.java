import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GraphBuilderTest {
    @Test
    public void getGraphInput() {
        GraphBuilder builder = new GraphBuilder("AB");

        assertEquals("AB", builder.getInput());
    }

    @Test
    public void buildUnweightedGraphWithSingleNode() {
        Map<Character, List<Character>> expected
            = new HashMap<Character, List<Character>>() {
                {
                    put('A', Arrays.asList('B'));
                }
            };
        GraphBuilder builder = new GraphBuilder("AB");

        assertEquals(expected, builder.buildUnweighted());
    }

    @Test
    public void buildUnweightedGraphWithThreeEdges() {
        Map<Character, List<Character>> expected
            = new HashMap<Character, List<Character>>() {
                {
                    put('A', Arrays.asList('B'));
                    put('B', Arrays.asList('C'));
                    put('C', Arrays.asList('D'));
                }
            };

        GraphBuilder builder = new GraphBuilder("AB, BC, CD");

        assertEquals(expected, builder.buildUnweighted());
    }

    @Test
    public void buildUnweightedGraphWithMultipleRoutes() {
        Map<Character, List<Character>> expected
            = new HashMap<Character, List<Character>>() {
                {
                    put('A', Arrays.asList('B', 'D', 'E'));
                    put('B', Arrays.asList('C'));
                    put('C', Arrays.asList('D', 'E'));
                    put('D', Arrays.asList('C', 'E'));
                    put('E', Arrays.asList('B'));
                }
            };
        GraphBuilder builder
            = new GraphBuilder("AB, BC, CD, DC, DE, AD, CE, EB, AE");

        assertEquals(expected, builder.buildUnweighted());
    }

    @Test
    public void buildWeightedGraphWithSingleNode() {
        Map<Character, HashMap<Character, Integer>> expected
            = new HashMap<Character, HashMap<Character, Integer>>() {
                {
                    put('A', new HashMap<Character, Integer>() {
                        {
                            put('B', 5);
                        }
                    });
                }
            };
        GraphBuilder builder = new GraphBuilder("AB5");

        assertEquals(expected, builder.buildWeighted());
    }

    @Test
    public void buildWeightedGraphWithMultipleNodes() {
        Map<Character, HashMap<Character, Integer>> expected
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
        GraphBuilder builder
            = new GraphBuilder("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

        assertEquals(expected, builder.buildWeighted());
    }
}
