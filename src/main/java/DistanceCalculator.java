import java.util.Map;
import java.util.HashMap;

public class DistanceCalculator {
    Map<Character, HashMap<Character, Integer>> graph;

    public DistanceCalculator(Map<Character, HashMap<Character, Integer>> inputGraph) {
        graph = inputGraph;
    }
}
