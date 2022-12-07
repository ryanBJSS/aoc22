import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

  public static void main(String[] args) throws IOException, URISyntaxException {

    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());
    var inputLines = Files.lines(path).map(String::trim).toList();

    var root = new Directory("/", null);
    var currentDirectory = root;

    for (String line : inputLines) {
      if (line.equals("$ cd /") || line.equals("$ ls")) {

      } else if (line.startsWith("dir")) {
        currentDirectory.addChild(new Directory(line.split("\s")[1], currentDirectory));
      } else if (line.equals("$ cd ..")) {
        currentDirectory = (Directory) currentDirectory.parent;
      } else if (!line.startsWith("$")) {
        currentDirectory.addChild(
            new File(line.split("\s")[1], currentDirectory, Integer.parseInt(line.split("\s")[0])));
      } else if (line.startsWith("$ cd")) {
        var dirName = line.split("\s")[2];
        currentDirectory = (Directory) currentDirectory.getChildren().stream()
            .filter(child -> child.name.equals(dirName)).findFirst().get();
      }
    }

    root.size();
    System.out.println(TooLazyToRefactor.partOne);
    System.out.println(TooLazyToRefactor.partTwo);
  }
}