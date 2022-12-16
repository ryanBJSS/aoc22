import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

  public static void main(String[] args) throws URISyntaxException, IOException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());

    var inputLines = Files.lines(path).toList();

    var allNodes = new ArrayList<Node>();
    inputLines.forEach(line -> allNodes.add(new InputParser(line).parse()));
    new Cave(allNodes).partTwo(4000000);

  }
}