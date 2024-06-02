package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import  org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private final String path1 = "src/test/resources/testfile1.json";
    private final String path2 = "src/test/resources/testfile2.json";
    private final String path3 = "src/test/resources/testfile1.yml";
    private final String path4 = "src/test/resources/testfile2.yml";

    private final Path pathStylish =
            Paths.get("src/test/resources/Expected/ExpectedStylish").toAbsolutePath().normalize();
    private final Path pathPlain =
            Paths.get("src/test/resources/Expected/ExpectedPlain").toAbsolutePath().normalize();
    private final Path pathJson =
            Paths.get("src/test/resources/Expected/ExpectedJson.json").toAbsolutePath().normalize();


    @Test
    public void test1() throws Exception {
        String expected = Files.readString(pathStylish);
        assertEquals(expected, Differ.generate(path1, path2));
    }

    @Test
    public void testStylish1() throws Exception {
        String expected = Files.readString(pathStylish);
        assertEquals(expected, Differ.generate(path1, path2, "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        String expected = Files.readString(pathStylish);
        assertEquals(expected, Differ.generate(path3, path4, "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        String expected = Files.readString(pathPlain);
        assertEquals(expected, Differ.generate(path1, path2, "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        String expected = Files.readString(pathPlain);
        assertEquals(expected, Differ.generate(path3, path4, "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(Files.readString(pathJson)),
                mapper.readTree(Differ.generate(path1, path2, "json")));
    }

    @Test
    public void testJson2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var expected = mapper.readTree(Files.readString(pathJson));
        assertEquals(expected, mapper.readTree(Differ.generate(path3, path4, "json")));
    }
}
