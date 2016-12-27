import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FileWriterTest {
    @Test
    public void testCanWriteToFile() {
        List<String> lines = Arrays.asList("Hello", "World");

        FileWriter.write(lines, "output.txt");

        assertEquals(lines, FileReader.readToArray("output.txt"));
    }

}
