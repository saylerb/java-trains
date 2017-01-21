import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Graph {
  private Map<Character, HashMap<Character, Integer>> graph;
  private String name;

  public Graph(Map<Character, HashMap<Character, Integer>> graph){
    this.graph = graph;
  }

  public Map<Character, HashMap<Character, Integer>> getGraph() {
    return this.graph;
  }

  public HashMap<Character, Integer> getNode(Character node) {
    return this.graph.get(node);
  }

  public Integer getDistance(Character node, Character destination) {
    return this.graph.get(node).get(destination);
  }

  public Set<Character> getNodes() {
    return this.graph.keySet();
  }

}
