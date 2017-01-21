import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceCalculator {
    private Graph graph;

    public DistanceCalculator(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public String calculateDistance(List<Character> route) {
        Integer totalDistance = 0;
        ArrayList<List<Character>> pairs = getNodePairs(route);

        if (routeExists(route)) {
            for (List<Character> pair: pairs) {
                char origin = pair.get(0);
                char destination = pair.get(1);

                totalDistance += graph.getDistance(origin, destination);
            }
            return String.valueOf(totalDistance);
        } else {
            return "NO SUCH ROUTE";
        }
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

        HashMap<Character, Integer> allEdges = graph.getNode(start);

        if (allEdges == null) {
            return false;
        } else if (allEdges.get(end) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean routeExists(List<Character> route) {
        return getNodePairs(route)
            .stream()
            .allMatch(edge -> edgeExists(edge));
    }
}
