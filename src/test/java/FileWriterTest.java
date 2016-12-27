import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FileWriterTest {
    @Test
    public void testCanWriteToFile() {
        List<String> lines = Arrays.asList("Hello", "World");

        FileWriter.write(lines, "src/test/java/test.txt");

        assertEquals(lines, FileReader.readToArray("src/test/java/test.txt"));
    }

}
