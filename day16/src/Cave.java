import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cave {

  private final List<Valve> valves;
  private final List<Route> routes;

  public Cave(List<Valve> valves) {
    this.valves = valves;
    this.routes = new ArrayList<>();
  }

  public long partOne() {
    int timeLeft = 0;
    long pressureDispersed = 0L;
    var start = valve("AA");

    calculateRoutes();
    return pressureDispersed;
  }

  private void calculateRoutes() {
    for (var valve : valves) {
      for (var child : valve.getTunnels()) {
        if (!routeExists(valve, child)) {
          routes.add(new Route(valve, child));
        }
      }
    }

    for (int i = 0; i < valves.size(); i++) {
      for (int j = i + 1; j < valves.size(); j++) {
        makeRoute(valves.get(i), valves.get(j), new ArrayList<>());
      }
    }
  }

  private void makeRoute(Valve from, Valve to, List<Valve> path) {
    if (from == to) {
      // Do nowt
    } else if (routeExists(from, to)) {
      // Do nowt
    } else {
      for (var child : from.getTunnels()) {
        path.add(from);
        if (routeExists(child, to)) {
          routes.add(new Route(from, child, List.of(child, to)));
        } else {
          for (var subChild : child.getTunnels()) {
            makeRoute(to, subChild, path);
          }
        }
      }
    }
  }

  private boolean routeExists(Valve from, Valve to) {
    Valve start;
    Valve end;
    if (from.getName().compareTo(to.getName()) < 0) {
      start = from;
      end = to;
    } else {
      start = to;
      end = from;

    }
    return routes.stream()
        .anyMatch(route -> route.getStart().equals(start) && route.getEnd().equals(end));
  }

  private Valve valve(String name) {
    return valves.stream().filter(valve -> valve.getName().equals(name)).findFirst().get();
  }
}
