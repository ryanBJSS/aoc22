import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
public class TestsTest {

  @Test
  void inputParser_itParsesFlowRate() throws IOException, URISyntaxException {
    var parsedValve = new InputParser(input()).parse().stream().findFirst().get();
    assertEquals(0, parsedValve.getFlowRate());
  }

  @Test
  void inputParser_itParsesTheName() throws IOException, URISyntaxException {
    var parsedValve = new InputParser(input()).parse().stream().findFirst().get();

    assertEquals("AA", parsedValve.getName());
  }

  @Test
  void inputParser_itParsesTheNameOfTunnels() throws IOException, URISyntaxException {
    var parsedValve = new InputParser(input()).parse().stream().findFirst().get();
    var expected = List.of("DD", "II", "BB");
    assertTrue(expected.containsAll(parsedValve.getTunnels().stream().map(Valve::getName).toList()));
  }

  @Test
  void inputParser_itLinksTheValues() throws IOException, URISyntaxException {
    var values =  new InputParser(input()).parse().stream().toList();
    var parsedValve = values.stream().filter( valve -> valve.getName().equals("EE")).findFirst().get();
    var expected = values.stream().filter( valve -> valve.getName().equals("DD")).findFirst().get();
    assertEquals(expected, parsedValve.getTunnels().stream().filter(valve -> valve.getName().equals("DD")).findFirst().get());
  }

  @Test
  void cave_itDoesPartOne() throws IOException, URISyntaxException {
    var cave = new Cave(valves());
    var expected = 1651;
    assertEquals(expected, cave.partOne());
  }


  public List<Valve> valves() throws IOException, URISyntaxException {
    return new InputParser(input()).parse();
  }

  public List<String> input() throws IOException, URISyntaxException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("testInput.txt")).toURI());

    return Files.lines(path).toList();
  }



}
