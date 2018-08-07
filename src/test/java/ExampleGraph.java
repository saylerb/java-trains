import java.util.HashMap;
import java.util.Map;

public class ExampleGraph {
    public static Graph createGraph() {
        Map<Character, HashMap<Character, Integer>> thing
            = new HashMap<Character, HashMap<Character, Integer>>() {
                {
                    put('A', new HashMap<Character, Integer>() {
                        {
                            put('B', 5);
                            put('D', 5);
                            put('E', 7);
                        }
                    });
                    put('B', new HashMap<Character, Integer>() {
                        {
                            put('C', 4);
                        }
                    });
                    put('C', new HashMap<Character, Integer>() {
                        {
                            put('D', 8);
                            put('E', 2);
                        }
                    });
                    put('D', new HashMap<Character, Integer>() {
                        {
                            put('C', 8);
                            put('E', 6);
                        }
                    });
                    put('E', new HashMap<Character, Integer>() {
                        {
                            put('B', 3);
                        }
                    });
                }
            };
        return new Graph(thing);
    }
}
