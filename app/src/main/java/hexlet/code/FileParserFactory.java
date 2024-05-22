package hexlet.code;

public class FileParserFactory {
    public static FileParser getFileParser(String path) throws Exception {
        if (path.endsWith(".json")) {
            return new JsonParser();
        } else if (path.endsWith(".yml") || path.endsWith(".yaml")) {
            return new YmlParser();
        } else {
            throw new Exception("Unsupported file type");
        }
    }
}
