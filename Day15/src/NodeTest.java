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
  void givenABeaconLocation_itCalculatesAllThePointsOthersCannotBe() {
    var sensor = new Node(8, 7);
    var beacon = new Node( 2, 10);
    assertEquals(181, sensor.ruleOut(beacon).size());
  }

}
