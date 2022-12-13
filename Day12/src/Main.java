import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
public class Main {

  public static void main(String[] args) throws URISyntaxException, IOException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());
    var inputLines = Files.lines(path).map(String::trim).toList();

    var points = new ArrayList<List<Point>>();
    Point start = null;

    for (var i = 0; i < inputLines.size(); i++) {
      points.add(new ArrayList<>());
      var line = Arrays.stream(inputLines.get(i).split("")).toList();
      for (var j = 0; j < line.size(); j++) {
        var height = (int) line.get(j).charAt(0);
        if (height == 83) {
          var toStart = new Point(i, j, 100);
          points.get(i).add(toStart);
          start = toStart;
        } else if (height == 69) {
          points.get(i).add(new Point(i, j, 122));
        } else {
          points.get(i).add(new Point(i, j, height));
        }
      }
    }

    for (int i = 0; i < points.size(); i++) {
      for (int j = 0; j < points.get(i).size(); j++) {

        var me = points.get(i).get(j);
        var myHeight = points.get(i).get(j).getHeight();

        if (i - 1 >= 0) {
          var above = points.get(i - 1).get(j);
          if (above.getHeight() <= myHeight + 1) {
            me.addVisitable(above);
          }
        }

        if (i + 1 < points.size()) {
          var below = points.get(i + 1).get(j);
          if (below.getHeight() <= myHeight + 1) {
            me.addVisitable(below);
          }
        }

        if (j - 1 >= 0) {

          var left = points.get(i).get(j - 1);
          if (left.getHeight() <= myHeight + 1) {
            me.addVisitable(left);
          }
        }

        if (j + 1 < points.get(i).size()) {
          var right = points.get(i).get(j + 1);
          if (right.getHeight() <= myHeight + 1) {
            me.addVisitable(right);
          }
        }
      }
    }
    List<Point> routes = new ArrayList<>();

    for (var row: points) {
      for (var p: row) {
        if(p.height == 97) {
          routes.add(start);
        }
      }
    }


    var visited = new HashSet<Point>();
    visited.addAll(routes);
    List<Integer> numberOfSteps = new ArrayList<>();

    while (!routes.isEmpty()) {
      var route = routes.remove(0);

      for (var nextStep: route.visitable) {
        if(!visited.contains(nextStep)) {
          if (nextStep.height == 122) {
            System.out.println("win");
            numberOfSteps.add(route.visits + 1);
          } else {
            nextStep.visits = (route.visits + 1);
            routes.add(nextStep);
          }
          visited.add(nextStep);
        }
      }
    }

    System.out.println(numberOfSteps);
  }
}

