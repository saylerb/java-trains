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

        String[] pairs = input.split(", ");
        System.out.println(pairs);

        Map<Character, List<Character>> result = new HashMap<Character, List<Character>>();

        for (String pair : pairs) {
            System.out.println(pair);
            char[] charPair = pair.toCharArray();

            char origin = charPair[0];
            char destination = charPair[1];

            List<Character> adjacentNodes = result.get(origin);

            if (adjacentNodes == null) {
                adjacentNodes = new ArrayList<Character>();
                adjacentNodes.add(destination);

                result.put(origin, adjacentNodes);

            } else {
                result.get(origin).add(destination);
            }
        }
        return result;
    }
}
