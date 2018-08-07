import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShortestPathFinder {
    private Graph graph;

    public ShortestPathFinder(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Character> getAdjacentNodes(Character node) {
        return new ArrayList<>(graph.getNode(node).keySet());
    }

    public HashMap<Character, Integer> generateCosts(char start) {
        List<Character> nodes = new ArrayList<>(graph.getNodes());

        HashMap<Character, Integer> costs = new HashMap<>();

        for (char node : nodes) {
            if (getAdjacentNodes(start).contains(node)) {
                costs.put(node, graph.getDistance(start, node));
            } else {
                costs.put(node, Integer.MAX_VALUE);
            }
        }
        return costs;
    }

    public HashMap<Character, Character> generatePredecessors(char start) {
        List<Character> nodes = new ArrayList<>(graph.getNodes());

        HashMap<Character, Character> parents = new HashMap<>();

        for (char node : nodes) {
            if (getAdjacentNodes(start).contains(node)) {
                parents.put(node, start);
            } else if (node != start) {
                parents.put(node, null);
            }
        }
        return parents;
    }

    public Character getLowestCostNode(
        char start,
        HashMap<Character, Integer> costs,
        List<Character> processed) {

        Integer lowestCost = Integer.MAX_VALUE;
        Character lowestCostNode = null;

        if (costs == null) {
            costs = generateCosts(start);
        }

        if (processed == null) {
            processed = new ArrayList<>();
        }

        for (char node : costs.keySet()) {

            Integer cost = costs.get(node);

            if (cost < lowestCost && !processed.contains(node)) {
                lowestCost = cost;
                lowestCostNode = node;
            }
        }
        return lowestCostNode;
    }

    public Integer findShortestPathDistance(char start, char end) {
        HashMap<Character, Integer> costs = generateCosts(start);
        HashMap<Character, Character> predecessors = generatePredecessors(start);

        Character currentNode = getLowestCostNode(start, null, null);
        List<Character> visited = new ArrayList<>();

        while (currentNode != null) {
            Integer cost = costs.get(currentNode);
            HashMap<Character, Integer> adjacentNodes = graph.getNode(currentNode);

            for (char adjacentNode : adjacentNodes.keySet()) {
                Integer newCost = cost + adjacentNodes.get(adjacentNode);

                if (newCost < costs.get(adjacentNode)) {
                    costs.put(adjacentNode, newCost);
                    predecessors.put(adjacentNode, currentNode);
                }
            }
            visited.add(currentNode);
            currentNode = getLowestCostNode(start, costs, visited);
        }
        return costs.get(end);
    }
}
