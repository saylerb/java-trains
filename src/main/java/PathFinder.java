import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    private Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Character> findAdjacentNodes(Character node) {
        return new ArrayList<>(graph.getNode(node).keySet());
    }

    private List<Character> buildVisitedNodes(Character start) {
        return new ArrayList<Character>() {
            {
                add(start);
            }
        };
    }

    public List<List<Character>> findPathsWithMaxStops(char start, char end, int max) {
        Route route = new Route(start, end, max);

        List<List<Character>> foundPaths = new ArrayList<>();
        List<Character> visitedNodes = buildVisitedNodes(start);

        return depthFirstSearchMaxStops(visitedNodes, foundPaths, start, route);
    }

    public List<List<Character>> findPathsWithExactStops(char start, char end, int max) {
        Route route = new Route(start, end, max);
        List<List<Character>> foundPaths = new ArrayList<>();
        List<Character> visitedNodes = buildVisitedNodes(start);

        return depthFirstSearchExactStops(visitedNodes, foundPaths, start, route);
    }

    public List<List<Character>> findPathsUpToMaxDistance(char start, char end, int max) {
        Route route = new Route(start, end, max);

        List<List<Character>> foundPaths = new ArrayList<>();
        List<Character> visitedNodes = buildVisitedNodes(start);

        return depthFirstSearchUpToDistance(visitedNodes, foundPaths, start, route);
    }

    private List<List<Character>> depthFirstSearchExactStops(
        List<Character> visitedNodes,
        List<List<Character>> foundPaths,
        Character currentNode,
        Route route) {

        if (visitedNodes.size() > route.getLimit() + 1) {
            return foundPaths;
        } else if (currentNode == route.getEnd() && visitedNodes.size() == route.getLimit() + 1) {
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
                depthFirstSearchExactStops(newVisitedNodes, foundPaths, adjacentNode, route);
            }
        }
        return foundPaths;
    }

    private List<List<Character>> depthFirstSearchMaxStops(
        List<Character> visitedNodes,
        List<List<Character>> foundPaths,
        Character currentNode,
        Route route) {

        if (visitedNodes.size() > route.getLimit() + 1) {
            return foundPaths;
        } else if (currentNode == route.getEnd() && visitedNodes.size() > 1) {
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
                depthFirstSearchMaxStops(newVisitedNodes, foundPaths, adjacentNode, route);
            }
        }
        return foundPaths;
    }

    private List<List<Character>> depthFirstSearchUpToDistance(
        List<Character> visitedNodes,
        List<List<Character>> foundPaths,
        Character currentNode,
        Route route) {

        String currentDistance = new DistanceCalculator(graph).calculateDistance(visitedNodes);

        if (Integer.parseInt(currentDistance) >= route.getLimit()) {
            return foundPaths;
        } else if (currentNode == route.getEnd() && visitedNodes.size() > 1) {
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
            depthFirstSearchUpToDistance(newVisitedNodes, foundPaths, adjacentNode, route);
        }
        return foundPaths;
    }
}
