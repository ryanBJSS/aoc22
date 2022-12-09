public class Head {

  private int x;
  private int y;
  private final Tail tail;

  public Head(int x, int y, Tail tail) {
    this.x = x;
    this.y = y;
    this.tail = tail;
  }

  public void moveUp(int distance) {
    for (int i = 0; i < distance; i++) {
      y += 1;
      tail.moveToHead(this);
    }
  }

  public void moveDown(int distance) {
    for (int i = 0; i < distance; i++) {
      y -= 1;
      tail.moveToHead(this);
    }

  }

  public void moveLeft(int distance) {
    for (int i = 0; i < distance; i++) {
      x -= 1;
      ;
      tail.moveToHead(this);
    }
  }

  public void moveRight(int distance) {
    for (int i = 0; i < distance; i++) {
      x += 1;
      tail.moveToHead(this);
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }


}
