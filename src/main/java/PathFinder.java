import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class PathFinder {
    private Map<Character, HashMap<Character, Integer>> graph;

    public PathFinder(Map<Character, HashMap<Character, Integer>> graph) {
        this.graph = graph;
    }

    public Map<Character, HashMap<Character, Integer>> getGraph() {
        return graph;
    }

    public List<Character> findAdjacentNodes(Character node) {
        return new ArrayList<Character>(graph.get(node).keySet());
    }

    public ArrayList<List<Character>> findPathWithMax(char start, char end, int max) {

        ArrayList<List<Character>> foundPaths = new ArrayList<List<Character>>();
        ArrayList<Character> visitedNodes = new ArrayList<Character>(){{ add(start); }};

        return depthFirstMax(visitedNodes, foundPaths, start, end, max);
    }

    private ArrayList<List<Character>> depthFirstMax(
            List<Character> visitedNodes,
            ArrayList<List<Character>> validRoutesFound,
            Character currentNode,
            Character end,
            Integer max) {

        if (visitedNodes.size() > max + 1) {
            return validRoutesFound;
        } else if (currentNode == end && visitedNodes.size() > 1) {
            validRoutesFound.add(visitedNodes);
        } else {
            List<Character> adjacentNodes = findAdjacentNodes(currentNode);

            for (char adjacentNode : adjacentNodes) {
                List<Character> temp = new ArrayList<Character>() {
                    {
                        addAll(visitedNodes);
                        add(adjacentNode);
                    }
                };

                depthFirstMax(temp, validRoutesFound, adjacentNode, end, max);
            }
        }
        return validRoutesFound;
    }
}
