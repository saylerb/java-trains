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
        Map<Character, List<Character>> result = new HashMap<Character, List<Character>>();

        char[] inputArr = input.toCharArray();

        char origin = inputArr[0];

        List<Character> finish = new ArrayList<Character>();

        finish.add(inputArr[1]);

        result.put(origin, finish);

        return result;
    }
}
