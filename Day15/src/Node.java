import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class Node {

  private final int x;
  private final int y;
  private Node closestBeacon;
  private final int d;

  public Node(int x, int y, Node closestBeacon) {
    this.x = x;
    this.y = y;
    this.closestBeacon = closestBeacon;
    if(closestBeacon != null) {
      this.d = Math.abs(x - closestBeacon.getX()) + Math.abs(y - closestBeacon.getY());
    } else {
      d = 0;
    }
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

  public int minX(int y1) {
    if(Math.abs(y - y1) > d) {
      return 0;
    }
    return (x - d) + Math.abs(y - y1);
  }

  public int maxX(int y1) {
    if(Math.abs(y - y1) > d) {
      return 0;
    }
    return (x + d) - Math.abs(y - y1);
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

  public boolean withinRange(int x1, int y1) {
    return Math.abs(x - x1) + Math.abs(y - y1) <= d;
  }

}
