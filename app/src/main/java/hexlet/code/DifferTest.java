package hexlet.code;

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
        assertEquals(file1Contents, Differ.parseJson("src/main/java/hexlet/code/file1.json"));
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

        Map<String, Object> resultMap = Differ.convert(file1Contents);

        assertEquals(expectedMap, resultMap);
    }
}
