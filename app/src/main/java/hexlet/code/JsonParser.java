package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonParser implements FileParser {
    @Override
    public Map<String, Object> parse(String path) throws Exception {
        String jsonContent = Files.readString(Paths.get(path).toAbsolutePath().normalize());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, Map.class);
    }
}
