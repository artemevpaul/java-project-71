package hexlet.code;

import hexlet.code.Formatters.Stylish;

import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String path1, String path2, String format)
            throws Exception {

        FileParser parser1 = FileParserFactory.getFileParser(path1);
        FileParser parser2 = FileParserFactory.getFileParser(path2);

        Map<String, Object> map1 = parser1.parse(path1);
        Map<String, Object> map2 = parser2.parse(path2);

        List<Map<String, Object>> result = Comparator.compare(map1, map2);

        return Formatter.formatStyle(result, format);
    }
    public static String generate(String path1, String path2)
            throws Exception {

        FileParser parser1 = FileParserFactory.getFileParser(path1);
        FileParser parser2 = FileParserFactory.getFileParser(path2);

        Map<String, Object> map1 = parser1.parse(path1);
        Map<String, Object> map2 = parser2.parse(path2);

        List<Map<String, Object>> result = Comparator.compare(map1, map2);

        return Stylish.formatStylish(result);
    }
}


