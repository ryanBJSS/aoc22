import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;


public class InputParserTest {

  @Test
  void returnsASensorAndABeacon() {
    var input = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15";
    var beacon = new Node(-2, 15);
    var sensor = new Node(2,18, beacon);

    assertEquals(sensor, new InputParser(input).parse());
  }



}
