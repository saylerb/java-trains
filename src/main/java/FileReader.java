import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    public static String readToString(String path) {
        String result = new String();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            result = stream
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        } catch (IOException error) {
            error.printStackTrace();
        }
        return result;
    }

    public static List<String> readToArray(String path) {
        List<String> result = new ArrayList<String>();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            result = stream
                .map(String::toString)
                .collect(Collectors.toList());
        } catch (IOException error) {
            error.printStackTrace();
        }
        return result;
    }

}
