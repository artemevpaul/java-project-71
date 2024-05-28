package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Stylish;
import hexlet.code.Formatters.Plain;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatStyle(
            List<Map<String, Object>> differences, String format) throws Exception {
        switch (format) {
            case "stylish":
                return Stylish.formatStylish(differences);
            case "plain":
                return Plain.formatPlain(differences);
            case "json":
                return Json.formatJson(differences);
            default:
                System.out.println("Format" + format + "is not correct!");
        }
        return Stylish.formatStylish(differences);
    }
}
