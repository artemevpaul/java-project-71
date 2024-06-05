package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import  org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static String pathStylish;
    private static String pathPlain;
    private static String pathJson;

    public String getPathToFile(String fileName) {
        switch (fileName) {
            case "testfile1":
                return "src/test/resources/testfile1.json";
            case "testfile2":
                return "src/test/resources/testfile2.json";
            case "testfile3":
                return "src/test/resources/testfile1.yml";
            case "testfile4":
                return "src/test/resources/testfile2.yml";
            default:
                throw new IllegalArgumentException("Invalid file name: " + fileName);
        }
    }
    private static Path generatePath(String fileName) {
        return Paths.get("src/test/resources", fileName).toAbsolutePath().normalize();
    }
    private static String readData(Path path) throws Exception {
        return Files.readString(path);
    }
    @BeforeAll
    public static void storePath() throws Exception {
        pathStylish = readData(generatePath("Expected/ExpectedStylish"));
        pathPlain = readData(generatePath("Expected/ExpectedPlain"));
        pathJson = readData(generatePath("Expected/ExpectedJson.json"));
    }
    @Test
    public void testDefault1() throws Exception {
        String expected = pathStylish;
        assertEquals(expected, Differ.generate(getPathToFile("testfile1"),
                getPathToFile("testfile2")));
    }
    @Test
    public void testDefault2() throws Exception {
        String expected = pathStylish;
        assertEquals(expected, Differ.generate(getPathToFile("testfile3"),
                getPathToFile("testfile4")));
    }

    @Test
    public void testStylish1() throws Exception {
        String expected = pathStylish;
        assertEquals(expected, Differ.generate(getPathToFile("testfile1"),
                getPathToFile("testfile2"), "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        String expected = pathStylish;
        assertEquals(expected, Differ.generate(getPathToFile("testfile3"),
                getPathToFile("testfile4"), "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        String expected = pathPlain;
        assertEquals(expected, Differ.generate(getPathToFile("testfile1"),
                getPathToFile("testfile2"), "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        String expected = pathPlain;
        assertEquals(expected, Differ.generate(getPathToFile("testfile3"),
                getPathToFile("testfile4"), "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(pathJson),
                mapper.readTree(Differ.generate(getPathToFile("testfile1"),
                        getPathToFile("testfile2"), "json")));
    }

    @Test
    public void testJson2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var expected = mapper.readTree(pathJson);
        assertEquals(expected, mapper.readTree(Differ.generate(getPathToFile("testfile3"),
                getPathToFile("testfile4"), "json")));
    }
}
