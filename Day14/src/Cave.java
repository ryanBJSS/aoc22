import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cave {

  private final List<Coordinate> points;
  private boolean continueDropping;
  private final int floor;

  public Cave(List<Coordinate> points) {
    this.points = new ArrayList<>(points);
    continueDropping = true;
    floor = points.stream().max(Comparator.comparing(Coordinate::getY)).get().getY() + 2;
  }

  public int partOne() {
    var sandCount = 0;
    var currentSand = Coordinate.drop();
    while (continueDropping) {

      sandCount++;

      while (!currentSand.isFrozen()) {
        tick(currentSand);
      }
      currentSand = Coordinate.drop();

    }

    System.out.println(sandCount - 1);
    return sandCount - 1;
  }

  public int partTwo() {
    var sandCount = 0;
    var currentSand = Coordinate.drop();
    while (continueDropping) {

      sandCount++;

      while (!currentSand.isFrozen() && continueDropping) {
        tickWithFloor(currentSand);
      }

      currentSand = Coordinate.drop();
    }

    System.out.println(sandCount);
    return sandCount;
  }

  private void tick(Coordinate coordinate) {

    if (fallingIntoTheVoid(coordinate)) {
      continueDropping = false;
      coordinate.freeze();
    } else if (canFallDown(coordinate)) {
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

  private void tickWithFloor(Coordinate coordinate) {

    if (canFallDown(coordinate)) {
      coordinate.fallDown();
    } else if (canFallLeft(coordinate)) {
      coordinate.fallLeft();
    } else if (canFallRight(coordinate)) {
      coordinate.fallRight();
    } else if (sandHasPluggedTheHole(coordinate)) {
      coordinate.freeze();
      continueDropping = false;
    } else {
      coordinate.freeze();
      points.add(coordinate);
    }
  }

  private static boolean sandHasPluggedTheHole(Coordinate coordinate) {
    return coordinate.getX() == 500 && coordinate.getY() == 0;
  }

  private boolean fallingIntoTheVoid(Coordinate coordinate) {
    return coordinate.getY() >= points.stream().max(Comparator.comparing(Coordinate::getY)).get()
        .getY();
  }

  private boolean canFallDown(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() && point.getY() == coordinate.getY() + 1)
        && coordinate.getY() + 1 < floor;
  }

  private boolean canFallLeft(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() - 1 && point.getY() == coordinate.getY() + 1)
        && coordinate.getY() + 1 < floor;
  }

  private boolean canFallRight(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() + 1 && point.getY() == coordinate.getY() + 1)
        && coordinate.getY() + 1 < floor;
  }
}
