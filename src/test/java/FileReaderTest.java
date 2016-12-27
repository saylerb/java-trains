import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FileReaderTest {
    @Test
    public void testCanReadFromFile() {
        String expected = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

        assertEquals(expected, FileReader.readToString("input.txt"));
    }

    @Test
    public void testCanReadFromFileToArray() {
        List<String> expected =
            Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");

        assertEquals(expected, FileReader.readToArray("input.txt"));
    }
}
