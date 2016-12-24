import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class PathFinder {
    private Map<Character, HashMap<Character, Integer>> graph;
    private Character start;
    private Character end;
    private Integer max;

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

        this.start = start;
        this.end = end;
        this.max = max;

        return depthFirstSearchMax(visitedNodes, foundPaths, start);
    }

    private ArrayList<List<Character>> depthFirstSearchMax(
            List<Character> visitedNodes,
            ArrayList<List<Character>> foundPaths,
            Character currentNode) {

        if (visitedNodes.size() > max + 1) {
            return foundPaths;
        } else if (currentNode == end && visitedNodes.size() > 1) {
            foundPaths.add(visitedNodes);
        } else {
            List<Character> adjacentNodes = findAdjacentNodes(currentNode);

            for (char adjacentNode : adjacentNodes) {
                List<Character> newVisitedNodes = new ArrayList<Character>() {
                    {
                        addAll(visitedNodes);
                        add(adjacentNode);
                    }
                };
                depthFirstSearchMax(newVisitedNodes, foundPaths, adjacentNode);
            }
        }
        return foundPaths;
    }
}
