import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import org.junit.jupiter.api.Test;


public class CaveTest {


  @Test
  void worksForPartOne() throws IOException, URISyntaxException {

    var input = input();

    var cave = new Cave(input);

    assertEquals(26, cave.partOne(10));
  }

  @Test
  void worksForPartTwo() throws IOException, URISyntaxException {

    var input = input();

    var cave = new Cave(input);

    assertEquals(56000011, cave.partTwo(20));
  }

  public ArrayList<Node> input() throws IOException, URISyntaxException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("testInput.txt")).toURI());


    var inputLines = Files.lines(path).toList();

    var allNodes = new ArrayList<Node>();
    inputLines.forEach(line -> allNodes.add(new InputParser(line).parse()));
    return allNodes;
  }

}
