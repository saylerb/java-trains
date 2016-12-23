import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DistanceCalculator {
    Map<Character, HashMap<Character, Integer>> graph;

    public DistanceCalculator(Map<Character, HashMap<Character, Integer>> inputGraph) {
        graph = inputGraph;
    }

    public Integer calculateDistance(List<Character> route) {
        Integer sum = 0;
        ArrayList<List<Character>> pairs = getNodePairs(route);

        for (List<Character> pair: pairs) {
            char origin = pair.get(0);
            char destination = pair.get(1);

            sum += graph.get(origin).get(destination);
        }
        return sum;
    }

    public ArrayList<List<Character>> getNodePairs(List<Character> route) {
        Integer totalNodes = route.size();

        ArrayList<List<Character>> result = new ArrayList<List<Character>>();

        for (int index = 0; index < totalNodes; index++) {
            if (index != totalNodes - 1) {
                result.add(Arrays.asList(route.get(index), route.get(index + 1)));
            }
        }

        return result;
    }

    public boolean edgeExists(List<Character> edge) {
        char start = edge.get(0);
        char end = edge.get(1);

        HashMap<Character, Integer> allEdges = graph.get(start);

        if (allEdges == null) {
            return false;
        } else if (allEdges.get(end) == null) {
            return false;
        } else {
            return true;
        }
    }
}
