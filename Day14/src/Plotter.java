import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Plotter {

  private final List<Coordinate> points;

  public Plotter(List<Coordinate> points) {
    this.points = points;
  }

  public List<Coordinate> plot() {
    var plotted = new ArrayList<Coordinate>();
    for (int i = 0; i < (points.size() - 1); i++) {
      var current = points.get(i);
      var target = points.get(i + 1);
      if (current.getX() == target.getX()) {
        IntStream.rangeClosed(Math.min(current.getY(), target.getY()), Math.max(current.getY(), target.getY())).forEach(currentY -> {
          plotted.add(new Coordinate(current.getX(), currentY));
        });
      } else {
        IntStream.rangeClosed(Math.min(current.getX(), target.getX()), Math.max(current.getX(), target.getX())).forEach(currentX -> {
          plotted.add(new Coordinate(currentX, current.getY()));
        });
      }
    }
    return plotted.stream().distinct().toList();
  }
}
