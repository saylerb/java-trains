import org.junit.Test;
import org.junit.Ignore;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GraphBuilderTest {
    @Test
    public void getGraph() {
        GraphBuilder builder = new GraphBuilder("AB");

        assertEquals("AB", builder.input);
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
}
