import hexlet.code.Differ;
import hexlet.code.FileParser;
import hexlet.code.FileParserFactory;
import hexlet.code.JsonParser;
import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {
    @Test
    public void testDiffer() throws Exception {
        String expectedResult = Files.readString(Paths.get("src/test/resources/Expected/ExpectedStylish"));

        String path1 = "src/test/resources/testfile1.yml";
        String path2 = "src/test/resources/testfile2.yml";


        String actualResult = Differ.generate(path1, path2, "stylish");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testDifferDefault() throws Exception {
        String expectedResult = Files.readString(Paths.get("src/test/resources/Expected/ExpectedStylish"));

        Assertions.assertEquals(Differ.generate("src/test/resources/testfile1.json",
                "src/test/resources/testfile2.json"), expectedResult);
    }

    @Test
    public void testReadStringFromFile() throws Exception {
        Path testFilePath = Paths.get("testfile2.yml");

        String expectedContent = "host: hexlet.io\n"
                + "timeout: 50\n"
                + "proxy: 123.234.53.22\n"
                + "follow: false\n";

        Files.writeString(testFilePath, expectedContent);

        String actualContent = Files.readString(testFilePath);

        assertEquals(expectedContent, actualContent);

        Files.delete(testFilePath);
    }

    @Test
    public void testCompareYml() throws Exception {
        String compareResult = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        String result = Differ.generate("src/main/java/hexlet/code/file1.yml",
                "src/main/java/hexlet/code/file2.yml", "stylish");

        assertEquals(compareResult, result);
    }

    @Test
    public void testPlain() throws Exception {
        String expected = Files.readString(Paths.get("src/test/resources/Expected/ExpectedPlain").toAbsolutePath()
                .normalize());
        assertEquals(expected, Differ.generate("src/test/resources/testfile1.yml",
                "src/test/resources/testfile2.yml", "plain"));
    }

    @Test
    public void testJson() throws Exception {
        String expected = Files.readString(Paths.get("src/test/resources/Expected/ExpectedJson").toAbsolutePath()
                .normalize());
        assertEquals(expected, Differ.generate("src/test/resources/testfile1.yml",
                "src/test/resources/testfile2.yml", "json"));
    }

    @Test
    public void testFactory() throws Exception {
        FileParser result = new JsonParser();
        assertTrue(FileParserFactory.getFileParser("src/main/java/hexlet/code/file1.json").getClass()
                .equals(result.getClass()));
    }
}
