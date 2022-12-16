import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cave {

  List<Node> nodes;

  public Cave(List<Node> nodes) {
    this.nodes = nodes;
  }

  public int partOne(int yHeight) {
    var allOut = new HashSet<Node>();
    nodes.stream().forEach(node -> allOut.addAll(node.ruleSimple(yHeight)));

    var beacons = nodes.stream().map(Node::getClosestBeacon).toList();

    var filtered = allOut.stream()
        .filter(node -> !beacons.contains(new Node(node.getX(), node.getY()))).toList();
    System.out.println(filtered.size());
    return filtered.size();
  }

  public int partTwo(int max) {

    Node location = null;
    for (int y = max; y > 0; y--) {
      var allRanges = new ArrayList<List<Integer>>();
      for (var node: nodes) {
        allRanges.add(List.of(node.minX(y), node.maxX(y)));
      }

      var startOfRanges = allRanges.stream().map(node -> node.get(0)).toList();
      var endOfRanges = allRanges.stream().map(node -> node.get(node.size() -1)).toList();

      var min = startOfRanges.stream().min(Integer::compare).get();
      var mmax= endOfRanges.stream().max(Integer::compare).get();

      var minCovered = Math.max(min, 0) == 0;
      var maxCovered = Math.min(mmax, max) == max;

      var mi = startOfRanges.stream().allMatch( minVal -> allRanges.stream().anyMatch( ran ->   {
        var check = Math.max(minVal - 1, 0);
        return ran.get(0) <= check && check <= ran.get(1);
      }));

      var ma = endOfRanges.stream().allMatch( maxVsl -> allRanges.stream().anyMatch( ran ->   {
        var check = Math.min(maxVsl + 1, max);
        return ran.get(0) <= check && check <= ran.get(1);
      }));

      if (!(minCovered && maxCovered && mi && ma)) {
        System.out.println("Found");
        for (int i = max; i > 0; i--) {
          int finalI = i;
          int finalY = y;
          var choose = nodes.stream().noneMatch(node -> node.withinRange(finalI, finalY));
          if (choose) {
            location = new Node(i, y);
            var outX = Long.valueOf(location.getX());
            var ouyY = Long.valueOf(location.getY());

            System.out.println((outX* 4000000) + ouyY);
            return (location.getX() * 4000000) + location.getY();
          }
        }
      }
    }
    return 0;

  }
}