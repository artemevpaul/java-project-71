import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {

    @Test
    public void testParseJson() throws Exception {
        String file1Contents = "{\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n"
                + "}";
        Assertions.assertEquals(file1Contents, Parser.parse("src/main/java/hexlet/code/file1.json"));
    }
    @Test
    public void testConvert() throws Exception {
        String file1Contents = "{\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n"
                + "}";
        Map<String, Object> expectedMap = Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false
        );

        Map<String, Object> resultMap = Parser.convert(file1Contents);

        assertEquals(expectedMap, resultMap);
    }

    @Test
    public void testParseYml() throws Exception {
        String ymlContents = "{\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n"
                + "}";
        assertEquals(Parser.parse("src/main/java/hexlet/code/file1.yml"), ymlContents);
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
        assertEquals((Differ.generate(Parser.convert(Parser.parse("src/main/java/hexlet/code/file1.yml")),
                Parser.convert(Parser.parse("src/main/java/hexlet/code/file2.yml")))), compareResult);
    }
}
