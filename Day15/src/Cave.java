import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cave {

  List<Node> nodes;

  public Cave(List<Node> nodes) {
    this.nodes = nodes;
  }

  public int partOne(int yHeight) {
    var allOut = new HashSet<Node>();
    nodes.forEach(node -> allOut.addAll(node.ruleSimple(yHeight)));

    var beacons = nodes.stream().map(Node::getClosestBeacon).toList();

    var filtered = allOut.stream()
        .filter(node -> !beacons.contains(new Node(node.getX(), node.getY()))).toList();
    System.out.println(filtered.size());
    return filtered.size();
  }

  public long partTwo(int max) {

    for (int y = max; y > 0; y--) {
      var allRanges = new ArrayList<List<Integer>>();
      for (var node: nodes) {
        allRanges.add(List.of(node.minX(y), node.maxX(y)));
      }

      var startOfRanges = allRanges.stream().map(node -> node.get(0)).toList();
      var endOfRanges = allRanges.stream().map(node -> node.get(node.size() - 1)).toList();

      var minCovered = Math.max(startOfRanges.stream().min(Integer::compare).get(), 0) == 0;
      var maxCovered = Math.min(endOfRanges.stream().max(Integer::compare).get(), max) == max;

      var leftCovered = startOfRanges.stream().allMatch( minVal -> allRanges.stream().anyMatch( ran ->   {
        var check = Math.max(minVal - 1, 0);
        return ran.get(0) <= check && check <= ran.get(1);
      }));

      var rightCovered = endOfRanges.stream().allMatch( maxVsl -> allRanges.stream().anyMatch( ran ->   {
        var check = Math.min(maxVsl + 1, max);
        return ran.get(0) <= check && check <= ran.get(1);
      }));

      if (!(minCovered && maxCovered && leftCovered && rightCovered)) {
        System.out.println("Found");
        for (int i = max; i > 0; i--) {
          int finalI = i;
          int finalY = y;
          var choose = nodes.stream().noneMatch(node -> node.withinRange(finalI, finalY));
          if (choose) {
            var outX = Long.valueOf(i);
            var ouyY = Long.valueOf(y);

            var result = (outX * 4000000) + ouyY;
            System.out.println(result);

            return result;
          }
        }
      }
    }
    return 0;
  }
}