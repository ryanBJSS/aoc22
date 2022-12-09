import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

  public static void main(String[] args) throws URISyntaxException, IOException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());
    var inputLines = Files.lines(path).map(String::trim).toList();

    var tail9 = new Tail(0, 0);
    var tail8 = new Tail(0, 0, tail9);
    var tail7 = new Tail(0, 0, tail8);
    var tail6 = new Tail(0, 0, tail7);
    var tail5 = new Tail(0, 0, tail6);
    var tail4 = new Tail(0, 0, tail5);
    var tail3 = new Tail(0, 0, tail4);
    var tail2 = new Tail(0, 0, tail3);
    var tail1 = new Tail(0, 0, tail2);
    var head = new Head(0, 0, tail1);

    System.out.println(tail1);
    for (var line: inputLines) {
      var split = line.split(" ");
      var direction = split[0];
      var distance = Integer.parseInt(split[1]);

      switch (direction) {
        case "R" -> head.moveRight(distance);
        case "U" -> head.moveUp(distance);
        case "L" -> head.moveLeft(distance);
        case "D" -> head.moveDown(distance);
      }
    }
    System.out.println(tail9.getVisited() + 1);
  }
}