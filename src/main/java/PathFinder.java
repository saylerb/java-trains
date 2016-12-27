import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ArrayList<List<Character>> findPathsWithMaxStops(char start, char end, int max) {
        setAttributes(start, end, max);

        ArrayList<List<Character>> foundPaths = new ArrayList<>();
        ArrayList<Character> visitedNodes = new ArrayList<Character>() {
            {
                add(start);
            }
        };
        return depthFirstSearchMaxStops(visitedNodes, foundPaths, start);
    }

    public ArrayList<List<Character>> findPathsWithExactStops(char start, char end, int max) {
        setAttributes(start, end, max);

        ArrayList<List<Character>> foundPaths = new ArrayList<>();

        ArrayList<Character> visitedNodes = new ArrayList<Character>() {
            {
                add(start);
            }
        };

        return depthFirstSearchExactStops(visitedNodes, foundPaths, start);
    }

    public ArrayList<List<Character>> findPathsUpToMaxDistance(char start, char end, int max) {
        setAttributes(start, end, max);

        ArrayList<List<Character>> foundPaths = new ArrayList<>();

        ArrayList<Character> visitedNodes = new ArrayList<Character>() {
            {
                add(start);
            }
        };

        return depthFirstSearchUpToDistance(visitedNodes, foundPaths, start);
    }

    private void setAttributes(char start, char end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
    }

    private ArrayList<List<Character>> depthFirstSearchExactStops(
            List<Character> visitedNodes,
            ArrayList<List<Character>> foundPaths,
            Character currentNode) {

        if (visitedNodes.size() > max + 1) {
            return foundPaths;
        } else if (currentNode == end && visitedNodes.size() == max + 1) {
            foundPaths.add(visitedNodes);
        } else {
            List<Character> adjacentNodes = findAdjacentNodes(currentNode);

            for (Character adjacentNode : adjacentNodes) {
                List<Character> newVisitedNodes = new ArrayList<Character>() {
                    {
                        addAll(visitedNodes);
                        add(adjacentNode);
                    }
                };
                depthFirstSearchExactStops(newVisitedNodes, foundPaths, adjacentNode);
            }
        }
        return foundPaths;
    }

    private ArrayList<List<Character>> depthFirstSearchMaxStops(
            List<Character> visitedNodes,
            ArrayList<List<Character>> foundPaths,
            Character currentNode) {

        if (visitedNodes.size() > max + 1) {
            return foundPaths;
        } else if (currentNode == end && visitedNodes.size() > 1) {
            foundPaths.add(visitedNodes);
        } else {
            List<Character> adjacentNodes = findAdjacentNodes(currentNode);

            for (Character adjacentNode : adjacentNodes) {
                List<Character> newVisitedNodes = new ArrayList<Character>() {
                    {
                        addAll(visitedNodes);
                        add(adjacentNode);
                    }
                };
                depthFirstSearchMaxStops(newVisitedNodes, foundPaths, adjacentNode);
            }
        }
        return foundPaths;
    }

    private ArrayList<List<Character>> depthFirstSearchUpToDistance(
            List<Character> visitedNodes,
            ArrayList<List<Character>> foundPaths,
            Character currentNode) {

        String currentDistance = new DistanceCalculator(graph).calculateDistance(visitedNodes);

        if (Integer.parseInt(currentDistance) >= max) {
            return foundPaths;
        } else if (currentNode == end && visitedNodes.size() > 1) {
            foundPaths.add(visitedNodes);
        }

        List<Character> adjacentNodes = findAdjacentNodes(currentNode);

        for (Character adjacentNode : adjacentNodes) {
            List<Character> newVisitedNodes = new ArrayList<Character>() {
                {
                    addAll(visitedNodes);
                    add(adjacentNode);
                }
            };
            depthFirstSearchUpToDistance(newVisitedNodes, foundPaths, adjacentNode);
        }
        return foundPaths;
    }
}
