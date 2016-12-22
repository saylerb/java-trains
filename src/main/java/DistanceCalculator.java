import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class DistanceCalculator {
    Map<Character, HashMap<Character, Integer>> graph;

    public DistanceCalculator(Map<Character, HashMap<Character, Integer>> inputGraph) {
        graph = inputGraph;
    }

    public Integer calculateDistance(List<Character> route) {
        return graph.get(route.get(0)).get(route.get(1));
    }
}
