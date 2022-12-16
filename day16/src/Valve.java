import java.util.ArrayList;
import java.util.List;

public class Valve {

  private final String name;
  private final int flowRate;
  private final List<Valve> tunnels;

  public Valve(String name, int flowRate) {
    this.name = name;
    this.flowRate = flowRate;
    this.tunnels = new ArrayList<>();
  }

  public void addConnection(Valve valve) {
    tunnels.add(valve);
  }
  public String getName() {
    return name;
  }

  public int getFlowRate() {
    return flowRate;
  }

  public List<Valve> getTunnels() {
    return tunnels;
  }
}
