package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static String parse(String path) throws Exception {
        //System.out.println(Files.readString(Paths.get(path)));
        return Files.readString(Paths.get(path));
    }
    public static Map<String, Object> convertYml(String ymlContent) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(ymlContent, Map.class);
    }

    public static Map<String, Object> convert(String jsonContent) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, Map.class);
    }
}
