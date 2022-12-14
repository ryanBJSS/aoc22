import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;


public class PlotterTest {

  @Test
  void vertical_itCanPlotALineBetweenCooridnates() {
    var points = List.of(new Coordinate(498, 4), new Coordinate(498, 5));

    var expected = List.of(
        new Coordinate(498, 4),
        new Coordinate(498, 5)
    );
    assertEquals(expected, new Plotter(points).plot());
  }

  @Test
  void vertical_itCanPlotALineWhereTheStartCoordinateIsHigherThanTheEnd() {
    var points = List.of(new Coordinate(503, 4), new Coordinate(503, 1));

    var expected = List.of(
        new Coordinate(503, 4),
        new Coordinate(503, 3),
        new Coordinate(503, 2),
        new Coordinate(503, 1)
    );
    assertEquals(expected.size(), new Plotter(points).plot().size());
    assertTrue(expected.containsAll(new Plotter(points).plot()));
  }

  @Test
  void vertical_itCanPlotALineLongerBetweenCooridnates() {
    var points = List.of(new Coordinate(498, 4), new Coordinate(498, 8));

    var expected = List.of(
        new Coordinate(498, 4),
        new Coordinate(498, 5),
        new Coordinate(498, 6),
        new Coordinate(498, 7),
        new Coordinate(498, 8)
    );
    assertEquals(expected, new Plotter(points).plot());
  }

  @Test
  void horizontal_itCanPlotALineBetweenCooridnates() {
    var points = List.of(new Coordinate(498, 4), new Coordinate(499, 4));

    var expected = List.of(
        new Coordinate(498, 4),
        new Coordinate(499, 4)
    );
    assertEquals(expected, new Plotter(points).plot());
  }

  @Test
  void horizontal_itCanPlotALineWhereTheStartCoordinateIsHigherThanTheEnd() {
    var points = List.of(new Coordinate(500, 4), new Coordinate(498, 4));

    var expected = List.of(
        new Coordinate(500, 4),
        new Coordinate(499, 4),
        new Coordinate(498, 4)
    );
    assertEquals(expected.size(), new Plotter(points).plot().size());
    assertTrue(expected.containsAll(new Plotter(points).plot()));
  }

  @Test
  void horizontal_itCanPlotALineLongerBetweenCooridnates() {
    var points = List.of(new Coordinate(400, 4), new Coordinate(405, 4));

    var expected = List.of(
        new Coordinate(400, 4),
        new Coordinate(401, 4),
        new Coordinate(402, 4),
        new Coordinate(403, 4),
        new Coordinate(404, 4),
        new Coordinate(405, 4)
    );
    assertEquals(expected, new Plotter(points).plot());
  }

  @Test
  void itPlotTheFirstSampleInput() {
    var points = List.of(new Coordinate(498, 4), new Coordinate(498, 6), new Coordinate(496, 6));

    var expected = List.of(
        new Coordinate(498, 4),
        new Coordinate(498, 5),
        new Coordinate(498, 6),
        new Coordinate(497, 6),
        new Coordinate(496, 6)
    );

    assertEquals(expected.size(), new Plotter(points).plot().size());
    assertTrue(expected.containsAll(new Plotter(points).plot()));
  }
}
