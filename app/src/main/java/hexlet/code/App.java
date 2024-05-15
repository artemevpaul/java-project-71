package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true
)
public class App implements Callable {
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() {
        try {
            String path1 = filepath1;
            String path2 = filepath2;

            Map<String, Object> map1 = Differ.convert(Differ.parseJson(path1));
            Map<String, Object> map2 = Differ.convert(Differ.parseJson(path2));

            String diff = Differ.generate(map1, map2);
            System.out.println(diff);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
