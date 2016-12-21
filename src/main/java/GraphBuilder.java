import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GraphBuilder {
    String input;

    public GraphBuilder(String inputGraph) {
        input = inputGraph;
    }

    public Map<Character, List<Character>> buildUnweighted() {
        char[] inputArr = input.toCharArray();
        char origin = inputArr[0];

        List<Character> destination = new ArrayList<Character>() {
            {
                add(inputArr[1]);
            }
        };

        Map<Character, List<Character>> result = new HashMap<Character, List<Character>>() {
            {
                put(origin, destination);
            }
        };

        return result;
    }
}
