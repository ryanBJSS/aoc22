import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {

  int x;
  int y;
  int height;
  List<Point> visitable = new ArrayList<>();
  public int visits = 0;

  public Point(int x, int y, int height) {
    this.x = x;
    this.y = y;
    this.height = height;
  }

  public void addVisitable(Point point) {
    visitable.add(point);
  }

  public int getHeight() {
    return height;
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + "]";
  }
}
