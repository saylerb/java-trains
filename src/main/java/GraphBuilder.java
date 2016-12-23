import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GraphBuilder {
    private String input;

    public GraphBuilder(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public Map<Character, List<Character>> buildUnweighted() {
        String[] pairs = input.split(", ");

        Map<Character, List<Character>> result
            = new HashMap<Character, List<Character>>();

        for (String pair : pairs) {
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

    public Map<Character, HashMap<Character, Integer>> buildWeighted() {
        String[] pairs = input.split(", ");

        Map<Character, HashMap<Character, Integer>> result
            = new HashMap<Character, HashMap<Character, Integer>>();

        for (String pair : pairs) {
            char[] charPair = pair.toCharArray();

            char origin = charPair[0];
            char destination = charPair[1];
            int weight = Character.getNumericValue(charPair[2]);

            HashMap<Character, Integer> adjacentNodes = result.get(origin);

            if (adjacentNodes == null) {
                adjacentNodes = new HashMap<Character, Integer>();
                adjacentNodes.put(destination, weight);

                result.put(origin, adjacentNodes);

            } else {
                result.get(origin).put(destination, weight);
            }
        }
        return result;
    }
}
