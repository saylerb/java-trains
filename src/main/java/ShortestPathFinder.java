import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ShortestPathFinder {
    private Map<Character, HashMap<Character, Integer>> graph;

    public ShortestPathFinder(Map<Character, HashMap<Character, Integer>> graph) {
        this.graph = graph;
    }

    public Map<Character, HashMap<Character, Integer>> getGraph() {
        return graph;
    }

    public List<Character> getAdjacentNodes(Character node) {
        return new ArrayList<Character>(graph.get(node).keySet());
    }

    public HashMap<Character, Integer> generateInitialCosts(char start, char end) {
        List<Character> nodes = new ArrayList<Character>(graph.keySet());

        HashMap<Character, Integer> costs = new HashMap<Character, Integer>();

        for (char node : nodes) {
            if (getAdjacentNodes(start).contains(node)) {
                costs.put(node, graph.get(start).get(node));
            } else {
                costs.put(node, Integer.MAX_VALUE);
            }
        }
        return costs;
    }
}
