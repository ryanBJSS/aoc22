import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cave {

  private final List<Coordinate> points;
  private boolean continueDropping;
  private final int floorLevel;
  private boolean caveHasFloor;

  public Cave(List<Coordinate> points) {
    this.points = new ArrayList<>(points);
    this.caveHasFloor = false;
    continueDropping = true;
    floorLevel = points.stream().max(Comparator.comparing(Coordinate::getY)).get().getY() + 2;
  }

  public Cave withFloor() {
    this.caveHasFloor = true;
    return this;
  }

  public int run() {

    var amountOfSandDropped = 0;
    var currentSand = Coordinate.drop();

    while (continueDropping) {

      while (!currentSand.isFrozen()) {
        tick(currentSand);
      }

      currentSand = Coordinate.drop();
      amountOfSandDropped++;
    }

    var result = amountOfSandDropped - 1;

    System.out.println(result);
    return result;
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
    } else if (sandHasPluggedTheHole()) {
      continueDropping = false;
      coordinate.freeze();
    } else {
      coordinate.freeze();
      points.add(coordinate);
    }
  }

  private boolean sandHasPluggedTheHole() {
    return points.stream().anyMatch(point -> point.getX() == 500 && point.getY() == 0);
  }

  private boolean fallingIntoTheVoid(Coordinate coordinate) {
    return !caveHasFloor && coordinate.getY() > points.stream()
        .max(Comparator.comparing(Coordinate::getY)).get().getY();
  }

  private boolean canFallDown(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() && point.getY() == coordinate.getY() + 1)
        && coordinate.getY() + 1 < floorLevel;
  }

  private boolean canFallLeft(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() - 1 && point.getY() == coordinate.getY() + 1)
        && coordinate.getY() + 1 < floorLevel;
  }

  private boolean canFallRight(Coordinate coordinate) {
    return points.stream().noneMatch(
        point -> point.getX() == coordinate.getX() + 1 && point.getY() == coordinate.getY() + 1)
        && coordinate.getY() + 1 < floorLevel;
  }
}
