import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {
    public static void write(List<String> lines, String path) {
        try {
            Files.write(Paths.get(path), lines);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
