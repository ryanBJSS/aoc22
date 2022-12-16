import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputParser {

  private static final Pattern flowRatePattern = Pattern.compile("\\d+");
  private final List<Valve> valves;

  public InputParser(List<String> input) {
    this.valves = input.stream().map(line -> {
      var parts = line.split(";");
      return new Valve(name(parts[0]), flowRate(parts[0]));
    }).toList();

    input.forEach(line -> {
      var parts = line.split(";");
      var name = name(parts[0]);
      var connectionParts = parts[1].split("tunnels lead to valves ");
      String connections;
      if(connectionParts.length == 1) {
        connections = parts[1].split("tunnel leads to valve ")[1];
      } else {
        connections = connectionParts[1];
      }
      linkTunnels(name, connections);
    });
  }

  public List<Valve> parse() {

    return valves;
  }

  private String name(String line) {
    return line.split(" ")[1];
  }

  private int flowRate(String line) {

    var flowRateMatcher = flowRatePattern.matcher(line);
    flowRateMatcher.find();
    return Integer.parseInt(flowRateMatcher.group());
  }

  private void linkTunnels(String name, String line) {
    Arrays.stream(line.split(", ")).forEach(connection -> valves.stream().filter(value -> value.getName()
        .equals(name)).findFirst().get().addConnection(valves.stream().filter(valve -> valve.getName().equals(connection)).findFirst().get()));
  }

}
