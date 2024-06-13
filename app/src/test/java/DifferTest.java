package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static String dataStylish;
    private static String dataPlain;
    private static String dataJson;

    private static Path generatePath(String fileName) {
        return Paths.get("src/test/resources", fileName).toAbsolutePath().normalize();
    }

    private static String readData(Path path) throws Exception {
        return Files.readString(path);
    }

    @BeforeAll
    public static void storePath() throws Exception {
        dataStylish = readData(generatePath("Expected/ExpectedStylish"));
        dataPlain = readData(generatePath("Expected/ExpectedPlain"));
        dataJson = readData(generatePath("Expected/ExpectedJson.json"));
    }

    @Test
    public void testDefault1() throws Exception {
        String expected = dataStylish;
        assertEquals(expected, Differ.generate(generatePath("testfile1.json").toString(),
                generatePath("testfile2.json").toString()));
    }

    @Test
    public void testDefault2() throws Exception {
        String expected = dataStylish;
        assertEquals(expected, Differ.generate(generatePath("testfile1.yml").toString(),
                generatePath("testfile2.yml").toString()));
    }

    @Test
    public void testStylish1() throws Exception {
        String expected = dataStylish;
        assertEquals(expected, Differ.generate(generatePath("testfile1.json").toString(),
                generatePath("testfile2.json").toString(), "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        String expected = dataStylish;
        assertEquals(expected, Differ.generate(generatePath("testfile1.yml").toString(),
                generatePath("testfile2.yml").toString(), "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        String expected = dataPlain;
        assertEquals(expected, Differ.generate(generatePath("testfile1.json").toString(),
                generatePath("testfile2.json").toString(), "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        String expected = dataPlain;
        assertEquals(expected, Differ.generate(generatePath("testfile1.yml").toString(),
                generatePath("testfile2.yml").toString(), "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(dataJson),
                mapper.readTree(Differ.generate(generatePath("testfile1.json").toString(),
                        generatePath("testfile2.json").toString(), "json")));
    }

    @Test
    public void testJson2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var expected = mapper.readTree(dataJson);
        assertEquals(expected, mapper.readTree(Differ.generate(generatePath("testfile1.yml").toString(),
                generatePath("testfile2.yml").toString(), "json")));
    }
}
