import java.util.HashMap;
import java.util.Map;

public class GraphBuilder {
    private String input;

    public GraphBuilder(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public Graph buildWeighted() {
        String[] pairs = input.split(", ");

        Map<Character, HashMap<Character, Integer>> result
            = new HashMap<>();

        for (String pair : pairs) {
            char[] charPair = pair.toCharArray();

            char origin = charPair[0];
            char destination = charPair[1];
            int weight = Character.getNumericValue(charPair[2]);

            HashMap<Character, Integer> adjacentNodes = result.get(origin);

            if (adjacentNodes == null) {
                adjacentNodes = new HashMap<>();
                adjacentNodes.put(destination, weight);

                result.put(origin, adjacentNodes);

            } else {
                result.get(origin).put(destination, weight);
            }
        }
        return new Graph(result);
    }
}
