package hexlet.code;

import java.util.Map;

public interface FileParser {
    Map<String, Object> parse(String filePath) throws Exception;
}
