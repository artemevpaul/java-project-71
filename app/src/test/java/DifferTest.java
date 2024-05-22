import hexlet.code.Differ;
import hexlet.code.FileParser;
import hexlet.code.FileParserFactory;
//import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {

//    @Test
//    public void testParseJson() throws Exception {
//        String file1Contents = "{\n"
//                + "  \"host\": \"hexlet.io\",\n"
//                + "  \"timeout\": 50,\n"
//                + "  \"proxy\": \"123.234.53.22\",\n"
//                + "  \"follow\": false\n"
//                + "}";
//        Assertions.assertEquals(file1Contents,
//        FileParserFactory.getFileParser("src/main/java/hexlet/code/file1.json"));
//    }
    @Test
    public void testReadStringFromFile() throws Exception {
        Path testFilePath = Paths.get("testfile.yml");

        String expectedContent = "host: hexlet.io\n"
                + "timeout: 50\n"
                + "proxy: 123.234.53.22\n"
                + "follow: false\n";

        Files.writeString(testFilePath, expectedContent);

        String actualContent = Files.readString(testFilePath);

        assertEquals(expectedContent, actualContent);

        Files.delete(testFilePath);
    }

//    @Test
//    public void testParseYml() throws Exception {
//        String ymlContents = "{\n"
//                + "  \"host\": \"hexlet.io\",\n"
//                + "  \"timeout\": 50,\n"
//                + "  \"proxy\": \"123.234.53.22\",\n"
//                + "  \"follow\": false\n"
//                + "}";
//        FileParser parser1 = FileParserFactory.getFileParser("src/main/java/hexlet/code/file1.yml");
//        Map<String, Object> map1 = parser1.parse("src/main/java/hexlet/code/file1.yml");
//        assertEquals(map1, ymlContents);
//    }

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
        FileParser parser1 = FileParserFactory.getFileParser("src/main/java/hexlet/code/file1.yml");
        FileParser parser2 = FileParserFactory.getFileParser("src/main/java/hexlet/code/file2.yml");

        Map<String, Object> map1 = parser1.parse("src/main/java/hexlet/code/file1.yml");
        Map<String, Object> map2 = parser2.parse("src/main/java/hexlet/code/file2.yml");

        String result = Differ.generate(map1, map2);

        assertEquals(compareResult, result);
    }
}
