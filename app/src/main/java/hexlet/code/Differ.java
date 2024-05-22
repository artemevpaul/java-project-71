package hexlet.code;

//import java.nio.file.Files;
//import java.nio.file.Paths;
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        Map<String, Object> tree = new TreeMap<>();

        for (var key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                tree.put(key, "- " + key + ": " + map1.get(key));
            } else if (!map1.get(key).equals(map2.get(key))) {
                tree.put(key, "- " + key + ": " + map1.get(key) + "\n  + " + key + ": " + map2.get(key));
            } else if ((map1.get(key)).equals(map2.get(key))) {
                tree.put(key, "  " + key + ": " + map1.get(key));
            }
        }

        for (var key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                tree.put(key, "+ " + key + ": " + map2.get(key));
            }
        }

        StringBuilder builder = new StringBuilder("{\n");
        tree.forEach((key, value) -> builder.append("  " + value + "\n"));
        builder.append("}");

        return builder.toString();
    }

    public static String compareYml(Map<String, Object> map1, Map<String, Object> map2) {
        return "";
    }
}


