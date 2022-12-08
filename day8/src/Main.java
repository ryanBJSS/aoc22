import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
            .getResource("input.txt")).toURI());
        var inputLines = Files.lines(path).map(String::trim).toList();

        var forest = new Forest();

        for (var line: inputLines) {

            var treeLine = Arrays.stream(line.split("")).map(Integer::parseInt).map(Tree::new).toList();
            forest.addTreeLine(treeLine);
        }
        System.out.println(forest.countVisible());
        System.out.println(forest.countViewing());
    }
}