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
    public void buildUnweightedGraph() {
        Map<Character, List<Character>> expected = new HashMap<Character, List<Character>>() {
            {
                put('A', Arrays.asList('B'));
            }
        };

        GraphBuilder builder = new GraphBuilder("AB");

        assertEquals(expected, builder.buildUnweighted());
    }

}
