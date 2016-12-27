import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public HashMap<Character, Integer> generateCosts(char start, char end) {
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

    public HashMap<Character, Character> generatePredecessors(char start,char end) {
        List<Character> nodes = new ArrayList<Character>(graph.keySet());

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
            char end,
            HashMap<Character, Integer> costs,
            List<Character> processed) {

        Integer lowestCost = Integer.MAX_VALUE;
        Character lowestCostNode = null;

        if (costs == null) {
            costs = generateCosts(start, end);
        }

        if (processed == null) {
            processed = new ArrayList<Character>();
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
        HashMap<Character, Integer> costs = generateCosts(start, end);
        HashMap<Character, Character> predecessors = generatePredecessors(start, end);

        Character currentNode = getLowestCostNode(start, end, null, null);
        List<Character> visited = new ArrayList<Character>();

        while (currentNode != null) {
            Integer cost = costs.get(currentNode);
            HashMap<Character, Integer> adjacentNodes = graph.get(currentNode);

            for (char adjacentNode : adjacentNodes.keySet()) {
                Integer newCost = cost + adjacentNodes.get(adjacentNode);

                if (newCost < costs.get(adjacentNode)) {
                    costs.put(adjacentNode, newCost);
                    predecessors.put(adjacentNode, currentNode);
                }
            }
            visited.add(currentNode);
            currentNode = getLowestCostNode(start, end, costs, visited);
        }
        return costs.get(end);
    }
}
