public class File extends Node{

  private int size;
  public File(String name, Node parent, int size) {
    this.name = name;
    this.parent = parent;
    this.size = size;
  }

  @Override
  public int size() {
    return size;
  }
}
