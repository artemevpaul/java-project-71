package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String path1, String path2, String format)
            throws Exception {

        String data1 = readData(path1);
        String data2 = readData(path2);

        String fileType1 = getFormat(path1);
        String fileType2 = getFormat(path2);

        Map<String, Object> map1 = Parser.parser(data1, fileType1);
        Map<String, Object> map2 = Parser.parser(data2, fileType2);

        List<Map<String, Object>> result = Comparator.compare(map1, map2);

        return Formatter.formatStyle(result, format);
    }
    public static String generate(String path1, String path2)
            throws Exception {
        return generate(path1, path2, "stylish");
    }
    public static String readData(String filepath) throws Exception {
        Path path = Paths.get(filepath.substring(filepath.indexOf("src")));
        return Files.readString(path);
    }

    public static String getFormat(String filepath) {
        return filepath.substring(filepath.indexOf(".") + 1);
    }

}


