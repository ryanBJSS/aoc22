import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;


public class NodeTest {

  @Test
  void calculatesTheManhattenDistanceToAnotherPoint() {
    var first = new Node(8, 17);
    var second = new Node(-2, 15);
    assertEquals(12, first.distance(second));
  }

  @Test
  void calculatesTheManhattenDistanceToSamplePoint() {
    var first = new Node(8, 7);
    var second = new Node( 2, 10);
    assertEquals(9, first.distance(second));
  }

  @Test
  void calculatesTheSmallestPossibleXValueForAGivenY() {
    var beacon = new Node(2,  10);
    var sensor = new Node(8, 7, beacon);
    assertEquals(2, sensor.minX(4));
    assertEquals(2, sensor.minX(10));
  }

  @Test
  void calculatesTheLargestPossibleXValueForAGivenY() {
    var beacon = new Node(2,  10);
    var sensor = new Node(8, 7, beacon);
    assertEquals(14, sensor.maxX(4));
    assertEquals(14, sensor.maxX(10));
  }

  @Test
  void calculatesTheSmallestPossibleXValueForAGivenY1() {
    var beacon = new Node(15,  3);
    var sensor = new Node(13, 2, beacon);
    assertEquals(0, sensor.minX(17));
  }

  @Test
  void calculatesTheLargestPossibleXValueForAGivenY1() {
    var beacon = new Node(15,  3);
    var sensor = new Node(13, 2, beacon);
    assertEquals(0, sensor.maxX(17));
  }

}
