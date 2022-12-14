import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cave {

  private final List<Coordinate> points;
  private boolean goOn;
  private final int floor;

  public Cave(List<Coordinate> points) {
    this.points = new ArrayList<>(points);
    goOn = true;
    floor = points.stream().max(Comparator.comparing(Coordinate::getY)).get().getY() + 2;
  }

  public int runTwo() {
    var sandCount = 0;
    var currentSand = new Coordinate(500, 0);
    while (goOn) {

      while (!currentSand.isFrozen() && goOn) {
        tickTwo(currentSand);
      }

      currentSand = new Coordinate(500, 0);
      sandCount++;
    }

    System.out.println(sandCount);
    return sandCount;
  }

  private void tickTwo(Coordinate coordinate) {

    if (canFallDown(coordinate)) {
      coordinate.fallDown();
    } else if (canFallLeft(coordinate)) {
      coordinate.fallLeft();
    } else if (canFallRight(coordinate)) {
      coordinate.fallRight();
    }
    else if(coordinate.getX() == 500 && coordinate.getY() == 0) {
      coordinate.freeze();
      goOn = false;
    }
    else {
      System.out.println("Sand settled at " + coordinate.getY());

      coordinate.freeze();
      points.add(coordinate);
    }
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
    return sandCount - 1;
  }

  private void tick(Coordinate coordinate) {

    if (fallingIntoTheVoid(coordinate)) {
      goOn = false;
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

  private boolean fallingIntoTheVoid(Coordinate coordinate) {
    return coordinate.getY() >= bottom().getY();
  }

  private Coordinate bottom() {
    return points.stream().max(Comparator.comparing(Coordinate::getY)).get();
  }

  private boolean canFallDown(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() && point.getY() == coordinate.getY() + 1) && coordinate.getY() + 1 < floor;
  }

  private boolean canFallLeft(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() - 1 && point.getY() == coordinate.getY() + 1) && coordinate.getY() + 1 < floor;
  }

  private boolean canFallRight(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() + 1 && point.getY() == coordinate.getY() + 1) && coordinate.getY() + 1 < floor;
  }
}
