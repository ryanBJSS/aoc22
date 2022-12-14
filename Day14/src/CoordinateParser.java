import java.util.Arrays;
import java.util.List;

public class CoordinateParser {

  public static List<Coordinate> parse(String line) {
    return Arrays.stream(line.split("->"))
        .map(String::trim)
        .map(element -> {
          var parts = element.split(",");
          return new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }).toList();
  }
}
