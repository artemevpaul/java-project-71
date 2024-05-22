package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class YmlParser implements FileParser {
    @Override
    public Map<String, Object> parse(String path) throws Exception {
        String ymlContent = Files.readString(Paths.get(path));
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(ymlContent, Map.class);
    }
}
