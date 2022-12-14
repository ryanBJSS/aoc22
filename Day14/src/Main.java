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
    var startingPoints = new ArrayList<Coordinate>();
    inputLines.forEach( line -> {
      var coords = CoordinateParser.parse(line);
      startingPoints.addAll(new Plotter(coords).plot());
    });

//    new Cave(startingPoints).run();
    new Cave(startingPoints).run();
  }
}