package hexlet.code.Formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String formatJson(List<Map<String, Object>> differences) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(differences);
    }
}
