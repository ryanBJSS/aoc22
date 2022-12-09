import java.util.HashSet;
import java.util.Set;

public class Tail {

  private int x;
  private int y;
  private Tail tail;
  private final Set<String> visited = new HashSet<>();

  public Tail(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Tail(int x, int y, Tail tail) {
    this.x = x;
    this.y = y;
    this.tail = tail;
  }

  public void moveToHead(Head head) {

    if(Math.abs(this.x - head.getX()) <= 1 && Math.abs(this.y - head.getY()) <= 1) {
      return;
    }
    if (overlap(head)) {
      return;
    } else if (tailIsBelowHead(head) && tailIsLeftOfHead(head)) {
      moveUp(1);
      moveRight(1);
    } else if (tailIsBelowHead(head) && tailIsRightOfHead(head)) {
      moveUp(1);
      moveLeft(1);
    } else if (tailIsAboveHead(head) && tailIsRightOfHead(head)) {
      moveDown(1);
      moveLeft(1);
    } else if (tailIsAboveHead(head) && tailIsLeftOfHead(head)) {
      moveDown(1);
      moveRight(1);
    } else if (tailIsLeftOfHead(head)) {
      moveRight(1);
    } else if (tailIsRightOfHead(head)) {
      moveLeft(1);
    } else if (tailIsBelowHead(head)) {
      moveUp(1);
    } else if (tailIsAboveHead(head)) {
      moveDown(1);
    }

    this.visited.add(x + "," + y);
    if(tail != null) {
      tail.moveToHead(new Head(x, y, null));
    }
  }

  public int getVisited() {
    return visited.size();
  }

  public void moveUp(int distance) {
    y += distance;
  }

  public void moveDown(int distance) {
    y -= distance;
  }

  public void moveLeft(int distance) {
    x -= distance;
  }

  public void moveRight(int distance) {
    x += distance;
  }

  private boolean overlap(Head head) {
    return this.x == head.getX() && this.y == head.getY();
  }

  private boolean tailIsAboveHead(Head head) {
    return y > head.getY();
  }

  private boolean tailIsBelowHead(Head head) {
    return y < head.getY();
  }

  private boolean tailIsRightOfHead(Head head) {
    return x > head.getX();
  }

  private boolean tailIsLeftOfHead(Head head) {
    return x < head.getX();
  }
}
