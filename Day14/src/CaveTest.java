import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;


public class CaveTest {

  @Test
  void itWorks() {

    var walls = List.of(
        new Coordinate(498, 4),
        new Coordinate(498, 5),
        new Coordinate(498, 6),
        new Coordinate(496, 6),
        new Coordinate(497, 6),
        new Coordinate(502, 4),
        new Coordinate(503, 4),
        new Coordinate(502, 5),
        new Coordinate(502, 6),
        new Coordinate(502, 7),
        new Coordinate(502, 8),
        new Coordinate(502, 9),
        new Coordinate(494, 9),
        new Coordinate(495, 9),
        new Coordinate(496, 9),
        new Coordinate(497, 9),
        new Coordinate(498, 9),
        new Coordinate(499, 9),
        new Coordinate(500, 9),
        new Coordinate(501, 9)
    );

    assertEquals(24, new Cave(walls).partOne());
  }

  @Test
  void itWorksForPart2() {

    var walls = List.of(
        new Coordinate(498, 4),
        new Coordinate(498, 5),
        new Coordinate(498, 6),
        new Coordinate(496, 6),
        new Coordinate(497, 6),
        new Coordinate(502, 4),
        new Coordinate(503, 4),
        new Coordinate(502, 5),
        new Coordinate(502, 6),
        new Coordinate(502, 7),
        new Coordinate(502, 8),
        new Coordinate(502, 9),
        new Coordinate(494, 9),
        new Coordinate(495, 9),
        new Coordinate(496, 9),
        new Coordinate(497, 9),
        new Coordinate(498, 9),
        new Coordinate(499, 9),
        new Coordinate(500, 9),
        new Coordinate(501, 9)
    );

    assertEquals(93, new Cave(walls).partTwo());
  }
}
