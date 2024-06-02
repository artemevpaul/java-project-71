package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> parser(String data, String format) throws Exception {
        ObjectMapper mapper = chooseFormat(format);
        return mapper.readValue(data, new TypeReference<>() { });
    }

    private static ObjectMapper chooseFormat(String format) {
        return "json".equals(format) ? new ObjectMapper() : new YAMLMapper();
    }
}
