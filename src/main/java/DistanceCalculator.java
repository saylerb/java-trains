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
        return graph.get(route.get(0)).get(route.get(1));
    }

    public ArrayList<List<Character>> getNodePairs(List<Character> route) {
        Integer totalNodes = route.size();

        ArrayList<List<Character>> result = new ArrayList<List<Character>>();

        for(int index = 0; index < totalNodes; index++) {
            System.out.println("Current index is: " + index);
            if (index != totalNodes - 1) {
                result.add(Arrays.asList(route.get(index), route.get(index + 1)));
            }
        }

        return result;
    }
}
