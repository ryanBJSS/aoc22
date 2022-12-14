import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;


public class CoordinateParserTest {

  @Test
  void itWorks() {

    var line = "498,4 -> 498,6 -> 496,6";

    var expected = List.of(
        new Coordinate(498, 4),
        new Coordinate(498, 6),
        new Coordinate(496, 6)
    );
    assertEquals(expected, CoordinateParser.parse(line));
  }
}
