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

//  public int partTwo(int max) {
//
//    var allExcluded = new HashSet<Node>();
//
//    Node location = null;
//    for (int i = max; i > 0; i--) {
//
//      if(part(i, max) != max + 1) {
//        System.out.println(i);
//        return i;
//      }
//      System.out.println(i);
//    }
//    return 0;
//  }

  public int partTwo(int max) {
    var allXCoords = IntStream.rangeClosed(0, max).boxed().collect(Collectors.toSet());

    Node location = null;
    for (int y = max; y > 0; y--) {
//      var covered = new HashSet<Integer>();
//      nodes.forEach(node -> covered.addAll(
//          IntStream.rangeClosed(node.minX(finalY), node.maxX(finalY)).boxed()
//              .filter(n -> n <= max && n >= 0).toList()));

      var min = 0;
      var mmax = max;

      for (var node : nodes) {
        var localMin = node.minX(y);
        var localMax = node.maxX(y);
        if (localMin <= min && localMax >= min) {
          min = localMax;
        }

        if (localMin <= mmax && localMax >= mmax) {
          mmax = localMin;
        }
      }

      if (mmax - 1 > min) {

        for (int i = max; i > 0; i--) {
          int finalI = i;
          int finalY = y;
          var choose = nodes.stream().noneMatch(node -> node.withinRange(finalI, finalY));
          if (choose) {
            location = new Node(i, y);
            System.out.println((location.getX()));
            System.out.println((location.getY()));
            System.out.println((location.getX() * 4000000) + location.getY());
            return (location.getX() * 4000000) + location.getY();
          }
        }
      } else {
        System.out.println("Skipped");
      }
      System.out.println("Checked " + y);
    }
    return 0;

  }
}

//  public int partTwo(int max) {
//
//    Node location = null;
//    for (int i = max; i > 0; i--) {
//      for (int j = 0; j < max + 1; j++) {
//
//        int finalI = i;
//        int finalJ = j;
//        var debug = nodes.stream().filter(node -> node.withinRange(finalI, finalJ)).toList();
//        var choose = nodes.stream().noneMatch(node -> node.withinRange(finalI, finalJ));
//        if(choose) {
//          location = new Node(i,j);
//          System.out.println((location.getX() * 4000000) + location.getY());
//          return (location.getX() * 4000000) + location.getY();
//        }
//      }
//      System.out.println("Checking " + i);
//    }
//    return 0;
//  }
