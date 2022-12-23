import java.util.List;

public class Route {

  private Valve start;
  private Valve end;
  private List<Valve> path;

  public Route(Valve start, Valve end) {
    this.start = start;
    this.end = end;
    this.path = List.of(end);
  }

  public Route(Valve start, Valve end, List<Valve> path) {
    this.start = start;
    this.end = end;
    this.path = path;
  }

  public Valve getStart() {
    return start;
  }

  public Valve getEnd() {
    return end;
  }
}
