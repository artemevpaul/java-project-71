package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    public static List<Map<String, Object>> compare(Map<String, Object> map1, Map<String, Object> map2)
            throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        for (var key : keySet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (!map2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "removed");
            } else if (!map1.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("status", "added");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("status", "updated");
            } else {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "unchanged");
            }
            result.add(map);
        }
        return result;
    }
}
