import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cave {

  private final List<Coordinate> points;
  private boolean goOn;

  public Cave(List<Coordinate> points) {
    this.points = new ArrayList<>(points);
    goOn = true;
  }

  public int run() {
    var sandCount = 0;
    var currentSand = new Coordinate(500, 0);
    while (goOn) {

      while (!currentSand.isFrozen()) {
        tick(currentSand);
      }

      currentSand = new Coordinate(500, 0);
      sandCount++;
    }

    System.out.println(sandCount - 1);
    return sandCount -1;
  }

  private void tick(Coordinate coordinate) {

    if (fallingIntoTheVoid(coordinate)) {
      goOn = false;
      coordinate.freeze();
    }
    else if (canFallDown(coordinate)) {
      coordinate.fallDown();
    } else if (canFallLeft(coordinate)) {
      coordinate.fallLeft();
    } else if (canFallRight(coordinate)) {
      coordinate.fallRight();
    } else {
      coordinate.freeze();
      points.add(coordinate);
    }
  }

  private boolean fallingIntoTheVoid(Coordinate coordinate) {
    var bottom = points.stream().max(Comparator.comparing(Coordinate::getY)).get();
    return coordinate.getY() >= bottom.getY();
  }

  private boolean canFallDown(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() && point.getY() == coordinate.getY() + 1);
  }

  private boolean canFallLeft(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() - 1 && point.getY() == coordinate.getY() + 1);
  }

  private boolean canFallRight(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() + 1 && point.getY() == coordinate.getY() + 1);
  }
}
