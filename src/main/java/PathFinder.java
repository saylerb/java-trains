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

    public ArrayList<List<Character>> findPathsWithMaxStops(
            Character start,
            Character end,
            Integer max) {

        ArrayList<List<Character>> foundPaths = new ArrayList<>();
        ArrayList<Character> visitedNodes = new ArrayList<Character>() {
            {
                add(start);
            }
        };

        this.start = start;
        this.end = end;
        this.max = max;

        return depthFirstSearchMaxStops(visitedNodes, foundPaths, start);
    }

    public ArrayList<List<Character>> findPathsWithExactStops(
            Character start,
            Character end,
            Integer max) {

        ArrayList<List<Character>> foundPaths = new ArrayList<>();

        ArrayList<Character> visitedNodes = new ArrayList<Character>() {
            {
                add(start);
            }
        };

        this.start = start;
        this.end = end;
        this.max = max;

        return depthFirstSearchExactStops(visitedNodes, foundPaths, start);
    }

    public ArrayList<List<Character>> findPathsUpToMaxDistance(
            Character start,
            Character end,
            Integer max) {

        ArrayList<List<Character>> foundPaths = new ArrayList<>();

        ArrayList<Character> visitedNodes = new ArrayList<Character>() {
            {
                add(start);
            }
        };

        this.start = start;
        this.end = end;
        this.max = max;

        return depthFirstSearchUpToDistance(visitedNodes, foundPaths, start);
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
                depthFirstSearchExactStops(
                        newVisitedNodes,
                        foundPaths,
                        adjacentNode);
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
                depthFirstSearchMaxStops(
                        newVisitedNodes,
                        foundPaths,
                        adjacentNode);
            }
        }
        return foundPaths;
    }

    private ArrayList<List<Character>> depthFirstSearchUpToDistance(
            List<Character> visitedNodes,
            ArrayList<List<Character>> foundPaths,
            Character currentNode) {

        String currentDistance
            = new DistanceCalculator(graph).calculateDistance(visitedNodes);

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
            depthFirstSearchUpToDistance(
                    newVisitedNodes,
                    foundPaths,
                    adjacentNode);
        }
        return foundPaths;
    }
}
