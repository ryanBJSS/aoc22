import java.util.Objects;

public class Coordinate {
  private int x;
  private int y;
  private boolean frozen;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
    this.frozen = false;
  }

  public static Coordinate drop() {
    return new Coordinate(500, 0);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void fallDown() {
    this.y++;
  }

  public void fallLeft() {
    this.x--;
    this.y++;
  }

  public void fallRight() {
    this.x++;
    this.y++;
  }

  public void freeze() {
    this.frozen = true;
  }

  public boolean isFrozen() {
    return frozen;
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordinate that = (Coordinate) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
//  public int compare(Coordinate o1, Coordinate o2) {
//    if (o1.equals(o2)) {
//      return 0;
//    } else if (o1.getY() > o2.getY()) {
//      return -1;
//    } else if (o1.getY() < o2.getY()) {
//      return 1;
//    }
//    return 0;
//  }
}
