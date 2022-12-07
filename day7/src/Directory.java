import java.util.ArrayList;
import java.util.List;

public class Directory extends Node {

  List<Node> children = new ArrayList<>();


  public Directory(String name, Node parent) {
    this.name = name;
    this.parent = parent;
  }

  public void addChild(Node child) {
    children.add(child);
  }

  public List<Node> getChildren() {
    return children;
  }

  @Override
  public int size() {
    var size = children.stream().map(Node::size).mapToInt(Integer::valueOf).sum();

    if(size <= 100000) {
      TooLazyToRefactor.partOne += size;
    }

    if(size >= 2558312) {
      if(size < TooLazyToRefactor.partTwo) {
        TooLazyToRefactor.partTwo = size;
      }
    }
    return size;

  }
}
