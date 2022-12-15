import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Node {

  private final int x;
  private final int y;
  private Node closestBeacon;

  public Node(int x, int y, Node closestBeacon) {
    this.x = x;
    this.y = y;
    this.closestBeacon = closestBeacon;
  }

  public Node(int x, int y) {
    this(x, y, null);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Node getClosestBeacon() {
    return closestBeacon;
  }

  @Override
  public String toString() {
    return "S:(" + x + "," + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Node node = (Node) o;
    return x == node.x && y == node.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public int distance(Node point) {
    return Math.abs(x - point.getX()) + Math.abs(y - point.getY());
  }

  public Set<Node> ruleSimple(int yHeight) {
    var maxDistance = distance(closestBeacon);
    var points = new HashSet<Node>();

    var rangeOfX = IntStream.rangeClosed(x - maxDistance - 10, x + maxDistance + 10).boxed()
        .toList();

    for (var i : rangeOfX) {
      var potentialNode = new Node(i, yHeight);
      if (distance(potentialNode) <= maxDistance) {
        points.add(potentialNode);
      }
    }

    return points;
  }

  public Set<Node> ruleOut(Node beacon) {
    var maxDistance = distance(beacon);
    var points = new HashSet<Node>();

    var rangeOfX = IntStream.rangeClosed(x - maxDistance - 10, x + maxDistance + 10).boxed()
        .toList();
    var rangeOfY = IntStream.rangeClosed(y - maxDistance - 10, y + maxDistance + 10).boxed()
        .toList();
    ;

    for (var i : rangeOfX) {
      for (var j : rangeOfY) {
        var potentialNode = new Node(i, j);
        if (distance(potentialNode) <= maxDistance) {
          points.add(potentialNode);
        }
      }
    }

    return points;
  }
}
