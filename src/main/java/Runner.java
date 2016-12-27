import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Runner {
    public static void main(String[] args) {

        String input = FileReader.readToString("input.txt");
        GraphBuilder builder = new GraphBuilder(input);

        Map<Character, HashMap<Character, Integer>> graph
            = builder.buildWeighted();

        DistanceCalculator calc = new DistanceCalculator(graph);

        PathFinder finder = new PathFinder(graph);
        ShortestPathFinder shorty = new ShortestPathFinder(graph);

        List<String> output = new ArrayList<String>();

        System.out.println("Writing to output file...");

        output.add(valueOf(calc.calculateDistance(Arrays.asList('A', 'B', 'C'))));
        output.add(valueOf(calc.calculateDistance(Arrays.asList('A', 'D'))));
        output.add(valueOf(calc.calculateDistance(Arrays.asList('A', 'D', 'C'))));
        output.add(valueOf(calc.calculateDistance(Arrays.asList('A', 'E', 'B', 'C', 'D'))));
        output.add(valueOf(calc.calculateDistance(Arrays.asList('A', 'E', 'D'))));

        output.add(valueOf(finder.findPathsWithMaxStops('C', 'C', 3).size()));
        output.add(valueOf(finder.findPathsWithExactStops('A', 'C', 4).size()));

        output.add(valueOf(shorty.findShortestPathDistance('A', 'C')));
        output.add(valueOf(shorty.findShortestPathDistance('B', 'B')));

        output.add(valueOf(finder.findPathsUpToMaxDistance('C', 'C', 30).size()));

        List<String> formattedOutput = IntStream.range(0, output.size())
            .mapToObj(index -> "Output #" + (index + 1) + ": " + output.get(index))
            .collect(Collectors.toList());

        formattedOutput.forEach(line -> System.out.println(line));

        FileWriter.write(formattedOutput, "output.txt");
        System.out.println("Output written!");
    }
}

